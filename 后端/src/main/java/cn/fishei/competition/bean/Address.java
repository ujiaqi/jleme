package cn.fishei.competition.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("address")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private int id;
    private int userId;
    private String name;
    private String gender;
    private String phone;
    private String latitude;
    private String longitude;
    private String province;
    private String city;
    private String district;
    private String street;
    private String detailAddr;
    private int defaultAddr;
    private int status;
    private String addTime;
}
