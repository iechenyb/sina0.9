package com.cyb.thread;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.date.DateUtil;
/**
 * To prove the thread can executed by order .
 * @author DHUser
 *
 */
public class OrderedExeThreads {
	static long start = 0;
	static long end = 0;
	static ExecutorService cachedThreadPool = Executors.newCachedThreadPool(); //����һ���ɻ����̳߳أ�����̳߳س��ȳ���������Ҫ���������տ����̣߳����޿ɻ��գ����½��̡߳� 
	static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);  //����һ�������̳߳أ��ɿ����߳���󲢷������������̻߳��ڶ����еȴ�
	static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5); //����һ�������̳߳أ�֧�ֶ�ʱ������������ִ��
	static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();  //����һ�����̻߳����̳߳أ���ֻ����Ψһ�Ĺ����߳���ִ�����񣬱�֤����������ָ��˳��(FIFO, LIFO, ���ȼ�)ִ��
	public static void main(String[] args) {
		Random random = new Random();  
		List<Future<Map<String,Object>>> results = new ArrayList<Future<Map<String,Object>>>();  
		ThreadPoolExecutor execute = (ThreadPoolExecutor) Executors.newFixedThreadPool(3); 
		for(int i = 1; i <= 10; i++) {  
            int number = random.nextInt(20);  
            FactorialCalculator task = new FactorialCalculator(number,i+"");  
			Future<Map<String,Object>> result = (Future<Map<String,Object>>) execute.submit(task);  
            results.add(result);  
        }  
		
		do{  
			try {  
			System.out.println("*******************************************");
            System.out.printf("Main: Number of Completed Tasks:%d\n",execute.getCompletedTaskCount());  
            for (int i=0; i<results.size(); i++) {  
                Future<Map<String,Object>> result=results.get(i);  
                System.out.printf("Main: Task %s: %s\n",i,result.isDone());  
            }  
              TimeUnit.MILLISECONDS.sleep(50);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }while(execute.getCompletedTaskCount() < results.size()); 
		System.out.println("*******************************************");
        System.out.printf("Main: Results\n"); 
        int id=0;
        for(Future<Map<String,Object>> fus : results) {  
            try {  
                System.out.printf("Main: Task %s: %s\n",fus.get().get("name"),fus.get().get("value"));  
            } catch (Exception e) {  
                e.printStackTrace();  
            } 
        }  
        execute.shutdown();
	}
	public void test(){
		for(int i=1;i<=10000;i++){
			try {
				//SwingUtilities.invokeLater(new MessageHandler(i));//one by one normal 100s.10ms
				 // SwingUtilities.invokeAndWait(new MessageHandler(i));//one by one slowly
				//fixedThreadPool.execute(new MessageHandler(i));//one by one slowly
				//singleThreadExecutor.execute(new MessageHandler(i));//one by one slowly
				// new Thread(new MessageHandler(i)).start();//no order 
				//cachedThreadPool.execute(new MessageHandler(i));//no order  fast
				  //scheduledThreadPool.schedule(new MessageHandler(i), 3, TimeUnit.SECONDS);  //��ʾ�ӳ�3��ִ�С�
				  //scheduledThreadPool.scheduleAtFixedRate(new MessageHandler(i), 1, 3, TimeUnit.SECONDS);//��ʾ�ӳ�1���ÿ3��ִ��һ�Ρ�  
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				//shutDown();
			}
		}
	}
	public void shutDown(ExecutorService service){
		service.shutdown();
	}
}

class MessageHandler implements Runnable{
	Log log = LogFactory.getLog(OrderedExeThreads.class);
	int i=0;
    public MessageHandler(int i){
    	this.i = i;
    }
	@Override
	public void run() {
		if(i==1){
			OrderedExeThreads.start = System.currentTimeMillis();
		}
		log.info(i);
		if(i==10000){
			OrderedExeThreads.end = System.currentTimeMillis();
			log.info(DateUtil.format(new Date(OrderedExeThreads.start), "yyyy-MM-dd HH:mm:ss.SSS")+"->"+DateUtil.format(new Date(OrderedExeThreads.end), "yyyy-MM-dd HH:mm:ss.SSS"));
			log.info("total time is "+(OrderedExeThreads.end-OrderedExeThreads.start)/1000+"s."+(OrderedExeThreads.end-OrderedExeThreads.start)%1000+"ms");
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}	
}
class MessageHandlerCall implements Callable<String>{
	Log log = LogFactory.getLog(OrderedExeThreads.class);
	int i=0;
    public MessageHandlerCall(int i){
    	this.i = i;
    }
	@Override
	public String call() throws Exception {
		if(i==1){
			OrderedExeThreads.start = System.currentTimeMillis();
		}
		log.info(i);
		if(i==10000){
			OrderedExeThreads.end = System.currentTimeMillis();
			log.info(DateUtil.format(new Date(OrderedExeThreads.start), "yyyy-MM-dd HH:mm:ss.SSS")+"->"+DateUtil.format(new Date(OrderedExeThreads.end), "yyyy-MM-dd HH:mm:ss.SSS"));
			log.info("total time is "+(OrderedExeThreads.end-OrderedExeThreads.start)/1000+"s."+(OrderedExeThreads.end-OrderedExeThreads.start)%1000+"ms");
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "";
	}	
}
class FactorialCalculator implements Callable<Map<String,Object>> {  
    
    private int number;  
    private String name;  
    public FactorialCalculator(int number,String name) {  
        super();  
        this.number = number;  
        this.name = name;
    }  
  
    @Override  
    public Map<String,Object> call() throws Exception {  
    	Map<String,Object> map = new HashMap<String,Object>();
        int result = 1;  
        if(number == 0 || number == 1) {  
            result = 1;  
        }else{  
            for(int i = 2; i <= number; i++) {  
                result *= i;  
                TimeUnit.MILLISECONDS.sleep(20);  
            }  
        }  
        System.out.printf("%s-%s: %d\n",Thread.currentThread().getName(),"task"+name,result);  
        map.put("name", "task"+name);
        map.put("value",result);
        return map;  
    }  
}