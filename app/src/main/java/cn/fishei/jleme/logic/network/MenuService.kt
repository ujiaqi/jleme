package cn.fishei.jleme.logic.network

import cn.fishei.jleme.logic.model.MenuResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MenuService {

    //请求所有商品
    @GET("menu/goodsLists")
    fun getGoodsList(@Query("id")id: String): Call<MenuResponse>

    //搜索商品
    @GET("menu/searchGoods")
    fun searchGoods(@Query("key")key: String): Call<MenuResponse>
}