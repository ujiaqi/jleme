package cn.fishei.competition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ErrorController {




    @GetMapping("/error/404")

    public String error404(){
        return "error/4xx";
    }

    @GetMapping("/error/500")
    public String error500(){
        return "error/5xx";
    }


}