package com.cyb.thread.localthread;
/**
 * 生成的序列号都是乱的
 * @author DHUser
 *
 */
public class TestClient extends Thread {  
    private GenerNumber sn;  

    public TestClient(GenerNumber sn) {  
        this.sn = sn;  
    }  

    public void run() {  
        for (int i = 0; i < 10; i++) {  
            // ④每个线程打出3个序列值  
            System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["  
                     + sn.getNextNum() + "]");  
        }  
    }  
}  
