package cn.fishei.competition.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("user")
public class User {
    private int id;

    private String username;

    private String phone;

    private String password;

    private String salt;

    private String userPhoto;

    private String email;

    private int state;

    private String joinTime;

    private String lastLogin;

    private int tag;

    private String token;
}
