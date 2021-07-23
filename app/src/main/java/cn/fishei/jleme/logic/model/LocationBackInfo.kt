package cn.fishei.jleme.logic.model

//接收地址接口数据 JSON->实体
data class LocationBackInfo (val result: ArrayList<LocationDetail>,val msg: String, val code: String)