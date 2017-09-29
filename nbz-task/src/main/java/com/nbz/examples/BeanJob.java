package com.nbz.examples;

import org.apache.log4j.Logger;

/**
 *
 */
public class BeanJob {

    private static final Logger log = Logger.getLogger(BeanJob.class);

    private String jobName;

    public void process(){
        log.error(">>>>>>>>>>>jobName = "+jobName);
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
