package com.cyb.redis.qutoes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;

import com.cyb.redis.RedisClient;

public class ShowTask implements Runnable{
    static Log log = LogFactory.getLog(PrepareDataUtils.class);
	@Override
	public void run() {
		RedisClient client = new RedisClient();
		Jedis jedis = client.getJedis();
		while(true){
			String str = jedis.hget(RealQutoesMapper.RealPrix+"sh600868", "systime");
			log.info(str);
		}
	}

}
