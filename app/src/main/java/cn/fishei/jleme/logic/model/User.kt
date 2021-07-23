package cn.fishei.jleme.logic.model

// 用户信息
data class User (
    var id: String, var username:String, val phone:String, val password:String, val salt:String,
    val userPhoto:String, val email:String, val state:Int, val joinTime:String,
    val lastLogin:String, val tag:Int, val token:String)