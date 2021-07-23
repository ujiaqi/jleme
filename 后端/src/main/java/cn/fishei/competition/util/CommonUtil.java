package cn.fishei.competition.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class CommonUtil {

    public static String[] usernameList = {"蒸羊羔儿", "蒸熊掌", "蒸鹿尾儿", "烧花鸭", "烧雏鸡", "烧子鹅", "炉猪", "松花小肚儿", "什锦苏盘儿", "饥太美儿", "熏鸡白肚儿", "清蒸哈什蚂", "茄子晒炉肉", "芙蓉燕菜", "锅烧鲤鱼", "蜜蜡肘子", "什锦葛仙米", "酱泼肉", "熘蟹黄", "八宝榛子酱"};

    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("_","");
    }

    public static String md5(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    public static String formatDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

}
