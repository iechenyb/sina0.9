package com.cyb.redis.qutoes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;

import com.cyb.page.Pagination;
import com.cyb.redis.RedisClient;
import com.cyb.url.UrlUtils;

public class ChildTask implements Runnable {
	static Log log = LogFactory.getLog(PrepareDataUtils.class);
	Pagination page;

	public ChildTask(Pagination page) {
		this.page = page;
	}

	@Override
	public void run() {
		RedisClient client = new RedisClient();
		Jedis jedis = client.getJedis();
		while (true) {
			try {
				StringBuffer codes = null;
				// 分页处理
				codes = new StringBuffer("");
				long max = page.getPageSize() * page.getCurrentPage() - 1;
				if (max >= page.getRecordCount()) {
					max = page.getRecordCount() - 1;
				}
				log.debug("正在计算第" + page.getCurrentPage() + "页,start="
						+ page.getOffset() + ",end=" + max);
				for (int i = page.getOffset(); i < max; i++) {
					codes.append(PrepareDataUtils.STOCKLIST.get(i).get("CODE")
							.toString()
							+ ",");
				}
				String url = PrepareDataUtils.qutoesUrl + codes.toString();
				InputStream is = UrlUtils.getStream(url);
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(is, "gbk"));
				String qutoes = "";
				String codeInfo = "";
				while ((qutoes = buffer.readLine()) != null) {
					try {
						qutoes = qutoes.replaceAll(" ", "");
						codeInfo = qutoes.split("=")[0];
						String exchange = codeInfo.substring(10, 12);
						final String code = codeInfo.substring(10,
								codeInfo.length());
						final String qutoesInfo = qutoes.split("=")[1];
						int len = qutoes.split("=")[1].split(",").length;						
						if("sz002713".equals(code)){
							String str = jedis.hget(RealQutoesMapper.RealPrix+"sz002713", "systime");
							log.info("["+PrepareDataUtils.total+"]"+PrepareDataUtils.threads+":"+str);
						}
						if (len > 10) {
							if("hk".equals(exchange)){
		            			 RealQutoesMapper.String2ObjectHK(code,qutoesInfo,jedis);
		            		}else{
		            			 RealQutoesMapper.String2Object(code,qutoesInfo,jedis);
		            		}
							log.debug("[normal]" + code + "," + qutoesInfo);
						} else {
							log.debug("[error]" + qutoesInfo);
						}
					} catch (Exception e) {
						e.printStackTrace();
						log.info("行情[" + qutoes + "]解析异常，可忽略！" + e.getMessage());
					}
				} // end while
				is.close();
				is = null;
				codes.delete(0, codes.length());
			} catch (Exception e) {
				log.info(e.toString());
			}
		}
	}
}
