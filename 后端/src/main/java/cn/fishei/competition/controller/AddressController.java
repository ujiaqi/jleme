package cn.fishei.competition.controller;

import cn.fishei.competition.bean.Address;
import cn.fishei.competition.bean.User;
import cn.fishei.competition.service.impl.AddressServiceImpl;
import com.alibaba.druid.sql.dialect.phoenix.ast.PhoenixObject;
import com.tencentcloudapi.cynosdb.v20190107.models.Addr;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressServiceImpl addressService;

    @RequestMapping("/insertAddress")
    public Map<String, Object> insertAddress(String name, String gender, String phone, String province, String city,
                                             String district, String street, String detailAddr, String token){

        Map<String, Object> map = new HashMap<>();

        if (token.isEmpty()){
            map.put("code", "error");
            map.put("msg", "无效令牌");
            return map;
        }

        User user = addressService.selectCheckToken(token);
        if (user == null){
            map.put("code", "error");
            map.put("msg", "无效用户");
            return map;
        }


        Address address = new Address();
        address.setUserId(user.getId());
        address.setName(name);
        address.setGender(gender);
        address.setPhone(phone);
        address.setProvince(province);
        address.setCity(city);
        address.setStreet(street);
        address.setDistrict(district);
        address.setDetailAddr(detailAddr);
        addressService.insertAddress(address);
        map.put("code", "ok");
        map.put("msg", "新增地址信息成功！");
        return map;
    }

    @RequestMapping("/deleteAddress")
    public Map<String, Object> deleteAddress(String id, String token){
        Map<String, Object> map = new HashMap<>();

        if (token.isEmpty()){
            map.put("code", "error");
            map.put("msg", "无效令牌");
            return map;
        }
        if(id.isEmpty()){
            map.put("code", "error");
            map.put("msg", "无效地址参数");
        }
        User user = addressService.selectCheckToken(token);
        if (user == null){
            map.put("code", "error");
            map.put("msg", "无效用户");
            return map;
        }
        Address address = addressService.queryAddressById(id);

        if (address == null || address.getUserId() != user.getId()){
            map.put("code", "error");
            map.put("msg", "非法请求");
        }else{
            try {
                int addressId = Integer.parseInt(id);
                addressService.deleteAddress(addressId);
                map.put("code", "ok");
                map.put("msg", "删除成功");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                map.put("code", "error");
                map.put("msg", "非法地址参数");
                return map;
            }
        }
        return map;
    }

    @RequestMapping("/addressLists")
    public Map<String, Object> queryAddress(String token){
        Map<String, Object> map = new HashMap<>();

        if (token.isEmpty()){
            map.put("code", "error");
            map.put("msg", "无效令牌");
            return map;
        }
        User user = addressService.selectCheckToken(token);
        if (user == null){
            map.put("code", "error");
            map.put("msg", "无效用户");
            return map;
        }

        List<Address> addresses = addressService.queryAddress(user.getId());
        map.put("code","ok");

        if (addresses == null){
            map.put("msg","null");
            return map;
        }
        map.put("result", addresses);
        map.put("msg","获取用户地址成功");
        return map;

    }


}
