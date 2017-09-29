package com.nbz.examples;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 多任务调度
 */
public class MulitJobSimpleScheduler extends AbstractSchedulerManage{

    private static final Logger log = Logger.getLogger(MulitJobSimpleScheduler.class);



    public void run() throws Exception {



        Date time = new Date();
        log.info("调度初始化时间: "+ DateFormatUtils.format(time,TIME_FORMAT));
        Date startTime1 = DateUtils.addSeconds(time,5);
        Date startTime2 = DateUtils.addSeconds(time,10);
        Date startTime3 = DateUtils.addSeconds(time,1);
        final Scheduler scheduler = getSchedulerFactory().getScheduler();
        Thread monitor = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    List<String> jobgroups = new ArrayList<String>();
                    List<String> triggergroups = new ArrayList<String>();
                    Set<String> pausetriggergroups = new TreeSet<String>();
                    try {
                        jobgroups = scheduler.getJobGroupNames();
                        triggergroups = scheduler.getTriggerGroupNames();
                        pausetriggergroups = scheduler.getPausedTriggerGroups();
                        Set<TriggerKey> triggetKeys = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());
                        if(null!=triggetKeys&&!triggetKeys.isEmpty()){
                            for(TriggerKey tk : triggetKeys){
                                log.error(tk+"state : "+scheduler.getTriggerState(tk));
                            }
                        }


                        log.error("jobgroups={" + ArrayUtils.toString(jobgroups) + "}");
                        log.error("triggergroups={" + ArrayUtils.toString(triggergroups) + "}");
                        log.error("pausetriggergroups={" + ArrayUtils.toString(pausetriggergroups) + "}");
                        log.error("triggetKeys={"+ArrayUtils.toString(triggetKeys)+"}");


                    } catch (SchedulerException e) {
                        e.printStackTrace();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        monitor.setDaemon(true);
        monitor.start();

        //第一个定时任务,简单调度
        JobDetail oneJob = newJob(SimpleJob.class).withIdentity("oneJob","jobgroup1").build();
        //第二个定时任务,同1为同组
        JobDetail twoJob = newJob(SimpleJob.class).withIdentity("twoJob","jobgroup2").build();
        //第三个定时任务,2组
        JobDetail threeJob = newJob(SimpleJob.class).withIdentity("threeJob","group2").build();

        //定义触发器
        //简单触发器，5秒后执行一次
        Trigger simpleTrigger1 = newTrigger().withIdentity("simpleTrigger1", "simpleTriggerGroup").startAt(startTime1).build();
        Trigger simpleTrigger2 = newTrigger().withIdentity("simpleTrigger2", "simpleTriggerGroup").startAt(startTime2).build();

        Trigger trigger1 = newTrigger().withIdentity("trigger1", "triggergroup1").startAt(startTime3)
                .withSchedule(simpleSchedule().withIntervalInSeconds(2).withRepeatCount(10)).build();
        Trigger trigger2 = newTrigger().withIdentity("trigger2", "triggergroup2").startAt(startTime3)
                .withSchedule(simpleSchedule().withIntervalInSeconds(2).withRepeatCount(10)).build();

        log.error("triggerkey="+trigger1.getKey()+"\njobkey="+trigger1.getJobKey());

        //scheduler.scheduleJob(oneJob,simpleTrigger1);
        //scheduler.scheduleJob(twoJob,simpleTrigger2);
        scheduler.scheduleJob(oneJob,trigger1);
        //scheduler.scheduleJob(twoJob,trigger2);
        log.error("triggerkey="+trigger1.getKey()+"\njobkey="+trigger1.getJobKey());
        scheduler.start();

        TimeUnit.SECONDS.sleep(5);
        log.info("暂停trigger1");
        //scheduler.pauseJob(new JobKey("oneJob","jobgroup1"));
        scheduler.pauseTrigger(new TriggerKey("trigger1","triggergroup1"));

        TimeUnit.SECONDS.sleep(5);
        log.info("启动trigger1");
        //scheduler.resumeJob(new JobKey("oneJob","jobgroup1"));
        scheduler.resumeTrigger(new TriggerKey("trigger1","triggergroup1"));

        /*TimeUnit.SECONDS.sleep(1);
        log.info("删除job1");
        scheduler.deleteJob(new JobKey("oneJob","jobgroup1"));
        TimeUnit.SECONDS.sleep(5);
        log.info("add job2");
        scheduler.scheduleJob(twoJob,trigger2);
        TimeUnit.SECONDS.sleep(10);*/
        log.info("------- Waiting 60 seconds... -------------");

        log.info("------- Shutting Down ---------------------");
        scheduler.shutdown(true);
        log.info("------- Shutdown Complete -----------------");




    }
}
