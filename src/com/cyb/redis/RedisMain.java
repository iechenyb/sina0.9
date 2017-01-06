package com.cyb.redis;

import redis.clients.jedis.Jedis;

public class RedisMain {
	public static void main(String[] args) {
		//key string list sortedset set hash
       //new RedisClient().show("list"); 
		//new RedisClient().readData();
		RedisClient client = new RedisClient();
		Jedis jedis = client.getJedis();
		for(int i=0;i<10000;i++){
			for(int j=0;j<10;j++){
				jedis.hset("Qutoes:"+i+":"+j, "name", "chenyb");
				jedis.hset("Qutoes:"+i+":"+j, "age", j+i+"");
				jedis.hset("Qutoes:"+i+":"+j, "tel", "139387"+i);
			}
		}
		System.out.println("over!");
		jedis.disconnect();
    }
}
