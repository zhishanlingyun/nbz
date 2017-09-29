package com.nbz.examples;

import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
public abstract class AbstractSchedulerManage implements SchedulerManage {

    protected SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    protected final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public SchedulerFactory getSchedulerFactory() {
        return schedulerFactory;
    }
}
