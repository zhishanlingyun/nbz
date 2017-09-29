package com.nbz.examples;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.Callable;

/**
 * 最简单的任务
 */
public class SimpleJob<T> implements Job {

    private static final Logger log = Logger.getLogger(SimpleJob.class);
    private int count1=0;
    private static int count2=0;

    public void execute(JobExecutionContext context) throws JobExecutionException {

        try {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>> SimpleJob hashcode-"+this.hashCode()+" process...");
            log.info("[count1="+(count1++)+"] [count2="+(count2++)+"]\n"+"content={"+context+"}");
            log.info("<<<<<<<<<<<<<<<<<<<<<<<<<< SimpleJob hashcode-"+this.hashCode()+" process end");
            int a = 12/0;
        } catch (Exception e) {
            log.error(e);
            JobExecutionException je = new JobExecutionException(e);
            //je.setRefireImmediately(true);  //不停尝试repeat
            //je.setUnscheduleFiringTrigger(true);
            //je.setUnscheduleAllTriggers(true);
            throw je;
        }

    }
}
