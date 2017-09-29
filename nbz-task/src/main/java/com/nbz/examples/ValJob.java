package com.nbz.examples;

import org.apache.log4j.Logger;
import org.quartz.*;

import java.util.Map;

/**
 * 传入参数的job
 */
public class ValJob implements Job{

    private static final Logger log = Logger.getLogger(ValJob.class);

    private String name;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            log.warn(">>>>>>>>> name = "+name);
            log.warn("context.getScheduler().getContext()->"+context.getScheduler().getMetaData());
            JobDataMap jobDataMap = context.getMergedJobDataMap();
            for(Map.Entry<String, Object> entry : jobDataMap.entrySet()){
                log.warn("key="+entry.getKey()+" value="+entry.getValue());
            }
            name = context.getMergedJobDataMap().getString("name");
            log.warn(">>>>>>>>> name = "+name );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
