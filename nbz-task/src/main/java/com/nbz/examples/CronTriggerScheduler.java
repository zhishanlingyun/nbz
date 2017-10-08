package com.nbz.examples;

import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Administrator on 2017/7/5 0005.
 */
public class CronTriggerScheduler extends AbstractSchedulerManage{

    private static final Logger log = Logger.getLogger(CronTriggerScheduler.class);


    public void run() throws Exception {
        Scheduler scheduler = super.getSchedulerFactory().getScheduler();
        try {

            JobDetail job1 = newJob(ValJob.class).withIdentity("job1", "group1").storeDurably(true).build();
            CronTrigger ct1 = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0/2 * * * * ?"))
                    .build();
            job1.getJobDataMap().put("name","张三");

            CronTriggerImpl cti = new CronTriggerImpl();
            cti.setCronExpression("0/2 * * * * ?");
            cti.setKey(new TriggerKey("trigger1","triggergroup1"));
            cti.setStartTime(new Date());
            cti.setJobGroup(job1.getKey().getGroup());
            cti.setJobName(job1.getKey().getName());
            //cti.setJobDataMap();

            scheduler.addJob(job1,false);
            scheduler.scheduleJob(cti);



            //scheduler.scheduleJob(job1,cti);
            scheduler.start();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("----- 异常 关闭调度器 ------");
            scheduler.shutdown();
        }
    }
}
