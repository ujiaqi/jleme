package cn.fishei.jleme.logic.model

//商品菜单接口数据  JSON->实体
data class MenuResponse (val msg: String, val code: String, val goods: List<Goods>)