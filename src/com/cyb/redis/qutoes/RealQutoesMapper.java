package com.cyb.redis.qutoes;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;

import com.cyb.date.DateUtil;

public class RealQutoesMapper {
	static String RealPrix="Qutoes:Real:";
	static Log log = LogFactory.getLog(RealQutoesMapper.class);
	public static void String2Object(String code,String msg,Jedis jedis){	
		long s = System.currentTimeMillis();
		String qutoes ="";
		try {
			String[] dataArr = msg.replaceAll("\"", "").replaceAll(";", "").split(",");
			jedis.hset(RealPrix+code, "code", code);
			jedis.hset(RealPrix+code, "name",dataArr[Contants.NAME] );
			jedis.hset(RealPrix+code, "open",dataArr[Contants.OPEN] );
			jedis.hset(RealPrix+code, "preclose",dataArr[Contants.PRECLOSE] );
			jedis.hset(RealPrix+code, "high",dataArr[Contants.HIGH] );
			jedis.hset(RealPrix+code, "low",dataArr[Contants.LOW] );
			jedis.hset(RealPrix+code, "price",dataArr[Contants.PRICE] );
			jedis.hset(RealPrix+code, "close",dataArr[Contants.PRICE] );
			jedis.hset(RealPrix+code, "day",dataArr[Contants.DAY] );
			jedis.hset(RealPrix+code, "time",dataArr[Contants.TIME] );
			jedis.hset(RealPrix+code, "columcash",dataArr[Contants.COLUMNCASH] );
			jedis.hset(RealPrix+code, "turnvolume",dataArr[Contants.TURNVOLUME] );
			jedis.hset(RealPrix+code, "systime",DateUtil.timeToMilis(new Date()) );
		/*	jedis.hset(RealPrix+code, "name","" );
			jedis.hset(RealPrix+code, "open","" );
			jedis.hset(RealPrix+code, "preclose","" );
			jedis.hset(RealPrix+code, "high","" );
			jedis.hset(RealPrix+code, "low","" );
			jedis.hset(RealPrix+code, "price","" );
			jedis.hset(RealPrix+code, "close","" );
			jedis.hset(RealPrix+code, "day","" );
			jedis.hset(RealPrix+code, "time","" );
			jedis.hset(RealPrix+code, "columcash","" );
			jedis.hset(RealPrix+code, "turnvolume","" );*/
			jedis.hset(RealPrix+code, "systime",DateUtil.timeToMilis(new Date()) );
		} catch (Exception e) {
			log.error(qutoes+"->"+e.toString());
		}
		long e = System.currentTimeMillis();
		log.info("处理记录数="+((e-s)/1000)+"秒"+(e-s)%1000+"毫秒");
	}
	public static void String2ObjectHK(String code,String msg,Jedis jedis ){		
		long s = System.currentTimeMillis();
		String qutoes ="";
		try {
			String[] dataArr = msg.replaceAll("\"", "").replaceAll(";", "").split(",");
			jedis.hset(RealPrix+code, "code", code);
			jedis.hset(RealPrix+code, "name",dataArr[Contants.ZNNAME_] );
			jedis.hset(RealPrix+code, "open",dataArr[Contants.OPEN_] );
			jedis.hset(RealPrix+code, "preclose",dataArr[Contants.PRECLOSE_] );
			jedis.hset(RealPrix+code, "high",dataArr[Contants.HIGH_] );
			jedis.hset(RealPrix+code, "low",dataArr[Contants.LOW_] );
			jedis.hset(RealPrix+code, "price",dataArr[Contants.PRICE_] );
			jedis.hset(RealPrix+code, "close",dataArr[Contants.PRICE_] );
			jedis.hset(RealPrix+code, "day",dataArr[Contants.DAY_] );
			jedis.hset(RealPrix+code, "time",dataArr[Contants.TIME_] );
			jedis.hset(RealPrix+code, "columcash",dataArr[Contants.CJE] );
			jedis.hset(RealPrix+code, "turnvolume",dataArr[Contants.CJL] );
			jedis.hset(RealPrix+code, "systime",DateUtil.timeToMilis(new Date()) );
			/*jedis.hset(RealPrix+code, "name","" );
			jedis.hset(RealPrix+code, "open","");
			jedis.hset(RealPrix+code, "preclose","" );
			jedis.hset(RealPrix+code, "high","" );
			jedis.hset(RealPrix+code, "low","");
			jedis.hset(RealPrix+code, "price","" );
			jedis.hset(RealPrix+code, "close","" );
			jedis.hset(RealPrix+code, "day","" );
			jedis.hset(RealPrix+code, "time","" );
			jedis.hset(RealPrix+code, "columcash","" );
			jedis.hset(RealPrix+code, "turnvolume","" );*/
			jedis.hset(RealPrix+code, "systime",DateUtil.timeToMilis(new Date()) );
		} catch (Exception e) {
			log.error(qutoes+"->"+e.toString());
			e.printStackTrace();
		}
		long e = System.currentTimeMillis();
		log.info("处理记录数="+((e-s)/1000)+"秒"+(e-s)%1000+"毫秒");
	}
}
