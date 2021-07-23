package cn.fishei.jleme.center

import java.util.function.DoubleToIntFunction

//定义全局变量
class LoginData {
    companion object{
        //登录的状态
        var LOGIN_STATUS: Int = 0
        const val CODE_COOKIE: String = ""
        //是否保存了发送验证码时候的Cookie，防止两次请求Cookie不一致导致的验证码失效
        var SAVE_COOKIE: Int = 0

        //这边主要解决的是在验证码登录的时候转到密码登录，但是密码登录返回时，Activity并不能直接到主页面，来个判断
        var TURN_MAIN: Int = 0
        //保存我的地址列表的数量
        var size:Int = 0
        var GET_ADDRESS = 0

        var DELETE:Int = 0
    }
}