package cn.fishei.jleme.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import cn.fishei.jleme.JilemeApplication
import cn.fishei.jleme.JilemeApplication.Companion.context
import cn.fishei.jleme.R
import cn.fishei.jleme.center.LoginData
import cn.fishei.jleme.logic.dao.GoodsDatabaseHelper
import cn.fishei.jleme.logic.model.ShopRecourse
import cn.fishei.jleme.ui.activity.MenuActivity
import cn.fishei.jleme.ui.fragment.MenuFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.shopping_cart_recyclerview.view.*
import retrofit2.http.DELETE
import kotlin.concurrent.thread

class ShopAdapter(val shopList: ArrayList<ShopRecourse>) : RecyclerView.Adapter<ShopAdapter.ViewHolder>(){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val sales:TextView = view.findViewById(R.id.shop_sales)
        val photo:ImageView = view.findViewById(R.id.shop_photo)
        val name:TextView = view.findViewById(R.id.shop_name)
        val taste:TextView = view.findViewById(R.id.shop_taste)
        val number:TextView = view.findViewById(R.id.shop_number)
        val price:TextView = view.findViewById(R.id.shop_price)
        val deletePhote:ImageView = view.findViewById(R.id.delete_good)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shopping_cart_recyclerview, parent, false)
        val viewHolder = ViewHolder(view)
        val dbHolder = GoodsDatabaseHelper(JilemeApplication.context, "Good.db", 1)
        viewHolder.deletePhote.setOnClickListener {
            val db = dbHolder.writableDatabase
            db.delete("Goods", "name = ?", arrayOf(viewHolder.name.text.toString()))
            shopList.removeAt(viewHolder.position)
            notifyItemRemoved(viewHolder.position)
            notifyItemRangeChanged(viewHolder.position,itemCount)



//            if(shopList.isNotEmpty()){
//                shopList.removeAt(viewHolder.layoutPosition)
//                notifyDataSetChanged()
//            }

        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shop = shopList[position]

        holder.sales.text = shop.sales.toString()
        Glide.with(JilemeApplication.context).load(shop.photo.toString()).into(holder.photo)
//        holder.photo.setImageResource(shop.photo)
        holder.name.text = shop.name
        when(shop.taste){
            "1" -> holder.taste.text = "口味：酸"
            "2" -> holder.taste.text = "口味：甜"
            "3" -> holder.taste.text = "口味：辣"
            "4" -> holder.taste.text = "口味：清淡"
            "5" -> holder.taste.text = "口味：正常"
            "6" -> holder.taste.text = "口味：重口"
            "7" -> holder.taste.text = "口味：偏咸"
            "8" -> holder.taste.text = "口味：偏甜"
            "9" -> holder.taste.text = "口味：微辣"
        }
        holder.number.text = shop.number.toString()
        holder.price.text = shop.price.toString()

    }

    override fun getItemCount(): Int {
        return shopList.size
    }



}