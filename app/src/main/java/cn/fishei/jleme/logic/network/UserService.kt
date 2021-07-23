package cn.fishei.jleme.logic.network

import cn.fishei.jleme.center.LoginData
import cn.fishei.jleme.logic.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface UserService {

    //获得验证码
    @GET("user/getCode")
    fun getCode(@Query("phone") phone: String): Call<PhoneBackInfo>

    //手机验证码登录
    @GET("user/loginPhone")
    fun userLogin(@Query("phone")phone: String, @Query("code")code:String, @Header("Cookie")cookie: String): Call<UserResponse>

    //获得用户收获数据
    @GET("address/addressLists")
    fun getLocation(@Query("token")token: String):Call<LocationBackInfo>

    //密码登录
    @GET("user/loginPassword")
    fun getPasswordLogin(@Query("phone")phone: String,@Query("password")password:String):Call<PasswordLoginInfo>




    //user/testLoginNoChecking

}