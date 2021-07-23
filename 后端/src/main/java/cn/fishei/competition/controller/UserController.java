package cn.fishei.competition.controller;

import cn.fishei.competition.bean.User;
import cn.fishei.competition.service.impl.UserServiceImpl;
import cn.fishei.competition.util.SendSms;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @RequestMapping("/getCode")
    public Map<String, Object> getCode(@Param("phone") String phone, HttpSession session){
        Map<String, Object> map = new HashMap<>();
        if (phone.length()!=11 || phone.isEmpty()){

            map.put("status","lengthError");
        }else{
            try {
                Long.parseLong(phone);
            }catch (Exception e){
                e.printStackTrace();
                map.put("status","numberError");
                return map;
            }
            String random=(int)((Math.random()*9+1)*100000)+"";
            session.setAttribute("code", random);
            SendSms.sendSms("+86" + phone, random, "806352");
            map.put("status","ok");
        }

        return map;
    }

    @RequestMapping("/loginPhone")
    public Map<String, Object> loginPhone(String phone, @Param("code") String code, HttpSession session){
        User user = new User();
        Map<String, Object> map = new HashMap<>();
        String trueCode = (String) session.getAttribute("code");
        if (!StringUtils.isEmpty(trueCode)){
            if(trueCode.equals(code)){
                user.setPhone(phone);
                map.put("code","ok");
                map.put("detailUser",userService.loginPhone(user));
                session.removeAttribute("code");
            }else {
                map.put("code","验证码有误");
            }
        }else{
            map.put("code", "验证码失效");
        }
        return map;
    }

    @RequestMapping("/testLoginNoChecking")
    public Map<String, Object> loginPhone(String phone, @Param("code") String code){

        User user = new User();
        Map<String, Object> map = new HashMap<>();


        if(!code.isEmpty()){
            user.setPhone(phone);
            map.put("code","ok");
            map.put("detailUser",userService.loginPhone(user));
            //session.removeAttribute("code");
        }else {
            map.put("code","验证码有误");
            map.put("detailUser", null);

        }

        return map;
    }

    @RequestMapping("/loginPassword")
    public Map<String, Object> loginPassword(String phone, @Param("password") String password){
        Map<String, Object> map = new HashMap<>();

        if (phone.isEmpty()||password.isEmpty()){
            map.put("code","参数异常");
        }else {
            map.put("code","ok");
            map.put("user",userService.loginPassword(phone,password));
        }
        return map;

    }

}
