package cn.fishei.jleme.map

import com.baidu.location.BDLocation
import com.baidu.location.BDLocationListener
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.MyLocationData
import com.baidu.mapapi.model.LatLng


/**
 * 定位SDK监听函数
 */
/*
class MyLocationListener : BDLocationListener {
    override fun onReceiveLocation(location: BDLocation) {
        // MapView 销毁后不在处理新接收的位置
        if (location == null || mMapView == null) {
            return
        }
        val locData = MyLocationData.Builder()
            .accuracy(location.radius) // 设置定位数据的精度信息，单位：米
            .direction(location.direction) // 此处设置开发者获取到的方向信息，顺时针0-360
            .latitude(location.latitude)
            .longitude(location.longitude)
            .build()
        // 设置定位数据, 只有先允许定位图层后设置数据才会生效
        mBaiduMap.setMyLocationData(locData)
        if (isFirstLoc) {
            isFirstLoc = false
            val latLng = LatLng(location.latitude, location.longitude)
            val builder: MapStatus.Builder = Builder()
            builder.target(latLng).zoom(20.0f)
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()))
        }
    }
}
*/
