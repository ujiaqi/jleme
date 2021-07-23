package cn.fishei.competition.service.impl;

import cn.fishei.competition.bean.Address;
import cn.fishei.competition.bean.User;
import cn.fishei.competition.mapper.AddressMapper;
import cn.fishei.competition.mapper.UserMapper;
import cn.fishei.competition.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl {
    @Resource
    private AddressMapper addressMapper;

    @Resource
    private UserMapper userMapper;

    public User selectCheckToken(String token){
        return userMapper.selectCheckToken(token);
    }

    public int insertAddress (Address address){
        address.setDefaultAddr(0);
        address.setAddTime(CommonUtil.formatDate(new Date()));
        address.setStatus(1);
        return addressMapper.insertAddress(address);
    }


    public int deleteAddress(int id){
        return addressMapper.deleteAddress(id);
    }

    public List<Address> queryAddress(int userId){

        return addressMapper.queryAddr(userId);

    }

    public Address queryAddressById(String id){
        return addressMapper.queryAddressById(Integer.parseInt(id));
    }

}
