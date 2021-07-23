package cn.fishei.competition.service.impl;

import cn.fishei.competition.bean.User;
import cn.fishei.competition.mapper.UserMapper;
import cn.fishei.competition.util.CommonUtil;
import cn.fishei.competition.util.SendSms;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl {

    @Resource
    private UserMapper userMapper;

    //public User findUserByPhone(String phone) {
    //    return userMapper.selectFindUserByPhone(phone);
    //}

    public Map<String, Object> loginPhone(User user){
        Map<String, Object> map = new HashMap<>();
        User u = userMapper.selectFindUserByPhone(user.getPhone());
        if (u != null){

            if (u.getState()!=0){
                String token = CommonUtil.generateUUID();
                userMapper.updateLoginByPhone(user.getPhone(), CommonUtil.formatDate(new Date()), token);
                map.put("token",token);
                map.put("user",u);
                map.put("status",1);
            }else{
                map.put("token",null);
                map.put("user",null);
                map.put("status",0);
            }

        }else {
            user.setSalt(CommonUtil.generateUUID().substring(0,5));
            String newPassword = CommonUtil.generateUUID().substring(0,6);
            user.setPassword(CommonUtil.md5(newPassword + user.getSalt()));
            user.setUserPhoto(String.format("https://fishei.cn/static/user/userPhoto/default%d.png", new Random().nextInt(12) + 1 ));
            user.setEmail(null);
            user.setState(1);
            user.setUsername(CommonUtil.usernameList[new Random().nextInt(21)]);
            user.setJoinTime(CommonUtil.formatDate(new Date()));
            user.setLastLogin(CommonUtil.formatDate(new Date()));
            user.setTag(1);
            user.setToken(CommonUtil.generateUUID());
            userMapper.insertRegisterUser(user);
            new Thread(){
                @SneakyThrows
                @Override
                public void run() {
                    super.run();
                    Thread.sleep(60000);
                    SendSms.sendSms("+86"+user.getPhone(),newPassword,"806444");
                }
            }.start();
            map.put("token",user.getToken());
            map.put("user",user);
            map.put("status","2");
        }
        return map;
    }


    public Map<String, Object> loginPassword(String phone, String password){
        Map<String, Object> map = new HashMap<>();
        User u = userMapper.selectFindUserByPhone(phone);
        if (u != null){
            if (u.getState()!=0){
                String s = CommonUtil.md5(password + u.getSalt());
                if (u.getPassword().equals(s)){
                    String token = CommonUtil.generateUUID();
                    userMapper.updateLoginByPhone(phone, CommonUtil.formatDate(new Date()), token);
                    map.put("token",token);
                    map.put("user",u);
                    map.put("status",1);
                }else{
                    map.put("token",null);
                    map.put("user",null);
                    map.put("status",4);
                }
            }else{
                map.put("token",null);
                map.put("user",null);
                map.put("status",0);
            }
            return map;
        }else{
            map.put("token",null);
            map.put("user",null);
            map.put("status",3);
        }
        return map;

        // 0为禁止用户，1为正常用户，2为新注册用户，3为密码登录的未注册用户, 4密码错误
    }

}
