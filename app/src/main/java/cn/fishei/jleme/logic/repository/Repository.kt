package cn.fishei.jleme.logic.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.liveData
import cn.fishei.jleme.logic.model.PhoneBackInfo
import cn.fishei.jleme.logic.model.User
import cn.fishei.jleme.logic.network.UserNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

//这块参考书本P618 P550
object Repository {

    fun sendCode(phone: String) = fire(Dispatchers.IO){
        val getCode = UserNetwork.sendCode(phone)

        if (getCode.status == "ok"){
            val result = getCode.status
            Result.success(result)
        }else{
            Result.failure(RuntimeException("Response status is ${getCode.status}"))
        }
    }

    fun userLogin(phone: String, code: String, cookie: String)= fire(Dispatchers.IO){
        val userResponse = UserNetwork.userLogin(phone, code, cookie)
        if (userResponse.code == "ok"){
            Result.success(userResponse)
        }else{
            Result.failure(RuntimeException("Response status is ${userResponse.code}"))
        }
    }

    //3
    fun getLocation(token:String) = fire(Dispatchers.IO){
        val locationResponse = UserNetwork.getLocation(token)
        if (locationResponse.code == "ok"){
            Result.success(locationResponse)
        }else{
            Result.failure(RuntimeException("Response status is ${locationResponse.code}"))
        }
    }

    fun getPasswordLogin(phone:String, password: String) = fire(Dispatchers.IO){
        val passwordLoginResponse = UserNetwork.getPasswordLogin(phone,password)
        if (passwordLoginResponse.user.status == 1 ){
            Result.success(passwordLoginResponse)
        }else{
            Result.failure(RuntimeException("Response status is ${passwordLoginResponse.user.status}"))
        }
    }

    //判断获得菜单情况
    fun getGoodsList(id: String) = fire(Dispatchers.IO){
        val goodListResponse = UserNetwork.getGoodsList(id)
        if (goodListResponse.code == "ok"){
            val goods = goodListResponse.goods
            Result.success(goods)
        }else{
            Result.failure(RuntimeException("Response status is ${goodListResponse.msg}"))
        }
    }


    //获得查询结果
    fun searchGoods(key: String) = fire(Dispatchers.IO){
        val goodListResponse = UserNetwork.searchGoods(key)
        if (goodListResponse.code == "ok"){
            val goods = goodListResponse.goods
            Result.success(goods)
        }else{
            Result.failure(RuntimeException("Response status is ${goodListResponse.msg}"))
        }
    }


    private fun<T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            }catch (e:Exception){
                Result.failure<T>(e)
            }
            emit(result)
        }

}