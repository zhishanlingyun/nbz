package com.nbz;

import com.nbz.examples.CronTriggerScheduler;
import com.nbz.examples.MulitJobSimpleScheduler;
import com.nbz.examples.SchedulerManage;
import com.nbz.examples.SimpleScheduler;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
public class QuartzTest {

    public static void main(String[] args) {
        try {
            //SchedulerManage manage = new SimpleScheduler();
            //SchedulerManage manage = new MulitJobSimpleScheduler();
            SchedulerManage manage = new CronTriggerScheduler();
            manage.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
