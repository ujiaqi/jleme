package cn.fishei.jleme.ui.adapter

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cn.fishei.jleme.JilemeApplication
import cn.fishei.jleme.JilemeApplication.Companion.context
import cn.fishei.jleme.R
import cn.fishei.jleme.logic.dao.GoodsDatabaseHelper
import cn.fishei.jleme.logic.model.Goods
import com.bumptech.glide.Glide

class MenuAdapter(private val goodsList: List<Goods>): RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val pic: ImageView = view.findViewById(R.id.goods_show_pic)
        val name: TextView = view.findViewById(R.id.goods_show_name)
        val price: TextView = view.findViewById(R.id.goods_show_price)
        val description: TextView = view.findViewById(R.id.goods_show_desc)
        val sales: TextView = view.findViewById(R.id.goods_show_sales)
        val tags: TextView = view.findViewById(R.id.goods_show_tags)
        val goodsId: TextView = view.findViewById(R.id.goods_id)
        val addPic: ImageView = view.findViewById(R.id.goods_add_pic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        val dbHelper = GoodsDatabaseHelper(context,"Good.db", 1)
        val viewHolder = ViewHolder(view)
        viewHolder.addPic.setOnClickListener {
            val position = viewHolder.adapterPosition
            val goods = goodsList[position]
            val db = dbHelper.writableDatabase
            val value = ContentValues().apply {
                put("name", goods.name)
                put("pic", goods.pic)
                put("price", goods.price)
                put("sales", goods.sales)
                put("tags", goods.tagsId)
            }
            db.insert("Goods", null, value)
            Toast.makeText(parent.context, goods.name, Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val goods = goodsList[position]

        Glide.with(JilemeApplication.context).load(goods.pic.toString()).into(holder.pic)
        Log.d("图片地址：", goods.pic)
        holder.name.text = goods.name
        holder.price.text = "￥${goods.price}"
        holder.description.text = goods.description
        holder.sales.text = "销量：${goods.sales}"
        when(goods.tagsId){
            "1" -> holder.tags.text = "口味：酸"
            "2" -> holder.tags.text = "口味：甜"
            "3" -> holder.tags.text = "口味：辣"
            "4" -> holder.tags.text = "口味：清淡"
            "5" -> holder.tags.text = "口味：正常"
            "6" -> holder.tags.text = "口味：重口"
            "7" -> holder.tags.text = "口味：偏咸"
            "8" -> holder.tags.text = "口味：偏甜"
            "9" -> holder.tags.text = "口味：微辣"
        }

        holder.goodsId.text = goods.id

    }

    override fun getItemCount() = goodsList.size
}