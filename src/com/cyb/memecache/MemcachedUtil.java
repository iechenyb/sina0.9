package com.cyb.memecache;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import com.cyb.collection.CollectionFactory;
import com.cyb.collection.User;
import com.cyb.socket.one2one.Message;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcachedUtil {
	// 创建全局的唯一实例
	protected static MemCachedClient mcc = new MemCachedClient();

	protected static MemcachedUtil memCached = new MemcachedUtil();

	// 设置与缓存服务器的连接池
	static {
		// 服务器列表和其权重
		String[] servers = { "127.0.0.1:11211" };
		Integer[] weights = { 3 };

		// 获取socke连接池的实例对象
		SockIOPool pool = SockIOPool.getInstance();

		// 设置服务器信息
		pool.setServers(servers);
		pool.setWeights(weights);

		// 设置初始连接数、最小和最大连接数以及最大处理时间
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		// 设置主线程的睡眠时间
		pool.setMaintSleep(30);

		// 设置TCP的参数，连接超时等
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);

		// 初始化连接池
		pool.initialize();

		// 压缩设置，超过指定大小（单位为K）的数据都会被压缩
		mcc.setCompressEnable(true);
		mcc.setCompressThreshold(64 * 1024);
	}

	/**
	 * 保护型构造方法，不允许实例化！
	 * 
	 */
	protected MemcachedUtil() {

	}

	/**
	 * 获取唯一实例.
	 * 
	 * @return
	 */
	public static MemcachedUtil getInstance() {
		return memCached;
	}

	/**
	 * 添加一个指定的值到缓存中.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, Object value) {
		return mcc.add(key, value);
	}

	public boolean add(String key, Object value, Date expiry) {
		return mcc.add(key, value, expiry);
	}

	public boolean replace(String key, Object value) {
		return mcc.replace(key, value);
	}

	public boolean replace(String key, Object value, Date expiry) {
		return mcc.replace(key, value, expiry);
	}
    public boolean delete(String key){
    	return mcc.delete(key);
    }
	/**
	 * 根据指定的关键字获取对象.
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return mcc.get(key);
	}

	public static void main(String[] args) {
		System.out.println("****");
		MemcachedUtil cache = MemcachedUtil.getInstance();
		cache.add("hello", "memcached Test");//往缓存里添加一个变量
		cache.delete("hello");
		mcc.set("hello", "new values");
		cache.add("hello", "memcached Test2");//往缓存里添加一个变量	
		cache.add("hello1", "memcached Test1");//往缓存里添加一个变量
		System.out.println("get value : " + cache.get("hello"));//从缓存里获取变量的值
		System.out.println("get value1 : " + cache.get("hello1"));//从缓存里获取变量的值
		System.out.println("****");
		cache.add("user", CollectionFactory.getUser());
		cache.add("map", CollectionFactory.getMap());
		cache.add("list", CollectionFactory.getList());
		User user = (User) cache.get("user");
		Map map = (Map) cache.get("map");
		System.out.println(" test Object :"+user.getName());
		System.out.println(" test Map :"+map.get("name"));
	}
	//如果Memcached缓存服务不打开，main函数运行会报错

}
