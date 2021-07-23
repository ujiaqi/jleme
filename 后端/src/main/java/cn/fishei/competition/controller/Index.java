package cn.fishei.competition.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Index {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/monitor")
    public String monitor(){ return "monitor";}

    @RequestMapping("/jleme")
    public String jleme(){
        return "jleme";
    }
}
