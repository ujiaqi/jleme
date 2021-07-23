package cn.fishei.jleme.logic.model


class UserResponse(var code:String, val detailUser: DetailUser){
    class DetailUser (val user: User, val token:String, val status:Int)
}



