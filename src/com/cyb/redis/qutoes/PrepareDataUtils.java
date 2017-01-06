package com.cyb.redis.qutoes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;

import com.cyb.comconection.ConnectionUtils;
import com.cyb.h2.H2Manager;
import com.cyb.page.Pagination;
import com.cyb.property.PropertyUtil;
import com.cyb.redis.RedisClient;
import com.cyb.url.UrlUtils;


public class PrepareDataUtils {
	static Log log = LogFactory.getLog(PrepareDataUtils.class);
	static String qutoesUrl;
	static long total;
	static long threads=0;
	static ThreadPoolExecutor execute;
	public static List<Map<String,Object>> STOCKLIST;
	public static void initStock(){
		H2Manager.start();
		qutoesUrl = PropertyUtil.getValueByKey("App", "qutoesURL");
		ConnectionUtils<Map<String,Object>> dbUtils = new ConnectionUtils<Map<String,Object>>();
		try {
			STOCKLIST = dbUtils.queryForMap("SELECT exCHANGE_ ||codE_  code,nvl(name_,'') name,industry FROM STOCK",Map.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		total = STOCKLIST.size();
		int pageSize = Integer.valueOf(PropertyUtil.get("drawBatchNum"));
		Pagination page= new Pagination(1,pageSize,total);
		int pageCount = page.getPageCount();
		threads = pageCount;
		execute = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(pageCount+1);
	}
	public static void calRealQutoes(){
		
		int count = 0;
		long s = System.currentTimeMillis();
			try{
				int pageSize = Integer.valueOf(PropertyUtil.get("drawBatchNum"));
				try{
					if(STOCKLIST==null||STOCKLIST.size()==0){return ;}
					long total = STOCKLIST.size();
					if(STOCKLIST!=null&&total>0){
						Pagination page= new Pagination(1,pageSize,total);
						int pageCount = page.getPageCount();
						for(int j=1;j<=pageCount;j++){//分页处理
							Pagination page_= new Pagination(j,pageSize,total);
							//execute.submit(new ChildTask(page_));
							//SwingUtilities.invokeLater(new ChildTask(page_));
							new Thread(new ChildTask(page_)).start();
						}//end 分页
					}//end not null if				
				}catch(Exception e){
					log.info(e.toString());
					e.printStackTrace();				
				}
				long e = System.currentTimeMillis();
				if(Boolean.valueOf(PropertyUtil.get("showrapid"))){
					log.info("处理记录数="+count+",数据处理速度="+count/(e-s)+"条/毫秒,共耗时="+((e-s)/1000)+"."+(e-s)%1000+"秒.毫秒");
				}
			}catch(Exception e){
				log.info(e.getMessage());
			}
		}
	public static void calRealQutoes1(){
		int count = 0;
		RedisClient client = new RedisClient();
		Jedis jedis = client.getJedis();
		long s = System.currentTimeMillis();
			try{
				int pageSize = Integer.valueOf(PropertyUtil.get("drawBatchNum"));
				try{
					String qutoesUrl = PropertyUtil.getValueByKey("App", "qutoesURL");
					if(STOCKLIST==null||STOCKLIST.size()==0){return ;}
					long total = STOCKLIST.size();
					if(STOCKLIST!=null&&total>0){
						StringBuffer codes = null;
						Pagination page= new Pagination(1,pageSize,total);
						int pageCount = page.getPageCount();
						for(int j=1;j<=pageCount;j++){//分页处理
							codes = new StringBuffer("");
							Pagination page_= new Pagination(j,pageSize,total);
							long max = page_.getPageSize()*j-1;
							if(max >=total){
								max = total-1;
							}
							log.debug("正在计算第"+page_.getCurrentPage()+"页,start="+page_.getOffset()+",end="+max);
							for(int i=page_.getOffset();i<max;i++){
							   codes.append(STOCKLIST.get(i).get("CODE").toString()+",");
							}
							String url = qutoesUrl+codes.toString();
							InputStream is = UrlUtils.getStream(url);
							BufferedReader buffer = new BufferedReader(new InputStreamReader(is,"gbk"));
				            String qutoes = ""; 
				            String codeInfo ="";
				            while((qutoes=buffer.readLine())!=null){			            	
					            	try{
						            	qutoes = qutoes.replaceAll(" ", "");
						            	codeInfo = qutoes.split("=")[0];
						            	String exchange = codeInfo.substring(10,12);
						            	final String code = codeInfo.substring(10,codeInfo.length());
						            	final String qutoesInfo = qutoes.split("=")[1];
						            	int len = qutoes.split("=")[1].split(",").length;
						            	if("sz002713".equals(code)){
											String str = jedis.hget(RealQutoesMapper.RealPrix+"sz002713", "systime");
											log.info("["+PrepareDataUtils.total+"]"+PrepareDataUtils.threads+":"+str);
										}
						            	if(len>10){
						            		count++;
						            		if("hk".equals(exchange)){
						            			 RealQutoesMapper.String2ObjectHK(code,qutoesInfo,jedis);
						            		}else{
						            			 RealQutoesMapper.String2Object(code,qutoesInfo,jedis);
						            		}
						            		log.info("[normal]"+code+","+qutoesInfo);
						               }else{
						            	   log.debug("[error]"+qutoesInfo);
						               }
						            }catch(Exception e){
						            	e.printStackTrace();
						            	log.info("行情["+qutoes+"]解析异常，可忽略！"+e.getMessage());
						            }
				            } //end while
				            is.close();
				            is = null;
				            codes.delete(0, codes.length());
				          // }
						}//end 分页
					}//end not null if				
				}catch(Exception e){
					log.info(e.toString());
					e.printStackTrace();				
				}
				long e = System.currentTimeMillis();
				if(Boolean.valueOf(PropertyUtil.get("showrapid"))){
					log.info("处理记录数="+count+",数据处理速度="+count/(e-s)+"条/毫秒,共耗时="+((e-s)/1000)+"."+(e-s)%1000+"秒.毫秒");
				}
			}catch(Exception e){
				log.info(e.getMessage());
			}
		}
     public static void main(String[] args) {
    	 PrepareDataUtils.initStock();
    	 PrepareDataUtils.calRealQutoes();
	}
}
