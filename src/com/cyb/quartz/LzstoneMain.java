package com.cyb.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;

public class LzstoneMain {
	 private static Scheduler sched;
     public static void run() throws Exception{
            //创建LzstoneTimeTask的定时任务
            @SuppressWarnings("static-access")
			JobDetail jobDetail = new JobDetail("lzstoneJob",sched.DEFAULT_GROUP,LzstoneTimeTask.class);
            //目标 创建任务计划
            CronTrigger trigger = new CronTrigger("lzstoneTrigger","lzstone","*/1 * * * * ?");
            //0 0 12 * * ? 代表每天的中午12点触发
            sched = new org.quartz.impl.StdSchedulerFactory().getScheduler();
            sched.scheduleJob(jobDetail,trigger);
            sched.start();
     }
     //停止
     public static void stop() throws Exception{
            sched.shutdown();
      }
     
     public static void main(String[] args) {
		try {
			LzstoneMain.run();
			if(LzstoneTimeTask.count>5){
				LzstoneMain.stop();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
//执行
