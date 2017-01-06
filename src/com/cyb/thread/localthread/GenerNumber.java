package com.cyb.thread.localthread;
/**
 * 每个线程都生成属于自己的连续的有序的序列号
 * @author DHUser
 *每个线程相互独立了，同样是 static 变量，对于不同的线程而言，
 *它没有被共享，而是每个线程各一份，这样也就保证了线程安全。
 * 也就是说，TheadLocal 为每一个线程提供了一个独立的副本！
 */
public class GenerNumber {
	 // ①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值  
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {  
        public Integer initialValue() {  
            return 0;  
        }  
    }; 
    
  
    // ②获取下一个序列值  
    public int getNextNum() {  
        seqNum.set(seqNum.get() + 1);  
        return seqNum.get();  
    }  
  
    public static void main(String[] args) {  
        GenerNumber sn = new GenerNumber();  
        // ③ 3个线程共享sn，各自产生序列号  
        TestClient t1 = new TestClient(sn);  
        TestClient t2 = new TestClient(sn);  
        TestClient t3 = new TestClient(sn);  
        t1.start();  
        t2.start();  
        t3.start();  
    }  
}
