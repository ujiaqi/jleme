package cn.fishei.competition.mapper;


import cn.fishei.competition.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface UserMapper {
    int insertRegisterUser(User user);

    User selectFindUserByPhone(String phone);

    int updateLoginByPhone(String phone, String lastLogin, String token);

    User selectCheckToken(String token);

    User selectUserByPassword(String phone, String password);

    int updateUserPhoto(String userPhoto, String token);


}
