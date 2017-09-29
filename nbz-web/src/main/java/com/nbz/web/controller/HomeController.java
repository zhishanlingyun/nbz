package com.nbz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/8/28 0028.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/index")
    public String index(){
        return "/home/index";
    }

}
