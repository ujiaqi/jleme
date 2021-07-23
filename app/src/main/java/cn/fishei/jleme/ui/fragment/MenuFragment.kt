package cn.fishei.jleme.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cn.fishei.jleme.JilemeApplication
import cn.fishei.jleme.R
import cn.fishei.jleme.logic.dao.GoodsDatabaseHelper
import cn.fishei.jleme.logic.model.ShopRecourse
import cn.fishei.jleme.ui.adapter.ShopAdapter
import com.baidu.mapapi.map.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.shopping_cart_recyclerview.*
import kotlin.concurrent.thread

//private lateinit var  mMapView: MapView
class MenuFragment : Fragment() {

//    private lateinit var  mMapView: MapView
//    private lateinit var mLocClient: LocationClient
//    private lateinit var  map: BaiduMap
//    val myListener = MyLocationListener()
//    private  var  isFirstLoc:Boolean  = true
    private val shopList = ArrayList<ShopRecourse>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

//    fun shopData(){
    private fun shopData(sales: Int, pic: String, name: String, taste: String, price: Double){

        shopList.add(ShopRecourse(sales, pic, name, taste, 1, price))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

   /* private val isPrice = 1

    private val handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                isPrice -> shop_all_price.text = total.toString()

            }

        }

    }*/


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        shop_button.setOnClickListener {

           /* val dbHelper =  GoodsDatabaseHelper(JilemeApplication.context, "Good.db", 1)

            //获取数据
            val db = dbHelper.writableDatabase
            val cursor = db?.query("Goods", null, null, null, null, null, null)
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        val price = cursor.getString(cursor.getColumnIndex("price")).toDouble()
                        total += price
                    } while (cursor.moveToNext())
                }
            }
            cursor?.close()*/

            /*thread {
                val msg = Message()
                msg.what = isPrice

                handler.sendMessage(msg)
            }*/

        }
    }



    override fun onResume() {
        super.onResume()

        val layoutManager = LinearLayoutManager(context)
        shoppingId.layoutManager  = layoutManager
        val adapter = ShopAdapter(shopList)
        shoppingId.adapter = adapter
        adapter.notifyDataSetChanged()
        shopList.clear()
        val dbHelper =  GoodsDatabaseHelper(JilemeApplication.context, "Good.db", 1)
        //获取数据
        val db = dbHelper.writableDatabase
        val cursor = db?.query("Goods", null, null, null, null, null, null)
        var total:Double = 0.0
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val price = cursor.getString(cursor.getColumnIndex("price")).toDouble()
                    val pic = cursor.getString(cursor.getColumnIndex("pic"))
                    val tags = cursor.getString(cursor.getColumnIndex("tags"))
                    val sales = cursor.getString(cursor.getColumnIndex("sales")).toInt()
                    shopData(sales, pic, name, tags, price)
                    total += price
                } while (cursor.moveToNext())
            }
        }
        cursor?.close()
        if (shopList.isNotEmpty()){
            menu_is_goods.visibility = View.GONE
        }else{
            menu_is_goods.visibility = View.VISIBLE
        }

        shop_all_price.text = total.toString()


    }



}
