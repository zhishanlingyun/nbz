package com.nbz.examples;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTimeUtils;
import org.joda.time.Days;
import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 简单调度
 */
public class SimpleScheduler extends AbstractSchedulerManage {

    private static final Logger log = Logger.getLogger(SimpleScheduler.class);

    public void run() throws Exception{

        Date time = new Date();
        log.info("调度初始化时间: "+DateFormatUtils.format(time,TIME_FORMAT));
        Date startTime = DateUtils.addSeconds(time,10);

        Scheduler scheduler = getSchedulerFactory().getScheduler();

        JobDetail jobDetail = newJob(SimpleJob.class).withIdentity("simpleJob1", "group1").build();

        Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(startTime).build();

        scheduler.scheduleJob(jobDetail,trigger);
        log.info("job key="+jobDetail.getKey()+"定时任务将在 "+DateFormatUtils.format(startTime,TIME_FORMAT)+" 执行");
        scheduler.start();
        log.info("调度任务开始...");

        TimeUnit.SECONDS.sleep(60);
        log.info("------- Waiting 60 seconds... -------------");

        log.info("------- Shutting Down ---------------------");
        scheduler.shutdown(true);
        log.info("------- Shutdown Complete -----------------");


    }
}
