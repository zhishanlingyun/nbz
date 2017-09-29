package com.nbz.web;

import org.springframework.beans.factory.InitializingBean;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
public class App implements InitializingBean{

    public void afterPropertiesSet() throws Exception {
        System.out.println("hello world");
    }

}
