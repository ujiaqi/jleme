package cn.fishei.jleme.logic.model

//密码登录后接口返回的信息
class PasswordLoginInfo(var code: String,val user:DetailUser){
    class DetailUser (val user: User, val token:String, val status:Int)
}