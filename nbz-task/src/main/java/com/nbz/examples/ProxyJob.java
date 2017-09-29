package com.nbz.examples;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/7/5 0005.
 */
public class ProxyJob implements Job {

    private Method method;

    public void execute(JobExecutionContext context) throws JobExecutionException {

        /*Object targetObj = context.getMergedJobDataMap().get

        method.invoke()*/
    }
}
