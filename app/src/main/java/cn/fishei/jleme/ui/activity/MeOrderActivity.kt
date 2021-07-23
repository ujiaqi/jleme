package cn.fishei.jleme.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.fishei.jleme.Info.BagInfo
import cn.fishei.jleme.Info.FoodInfo
import cn.fishei.jleme.R
import kotlinx.android.synthetic.main.activity_me_order.*

class MeOrderActivity : BaseActivity() {
    private val foodList:ArrayList<FoodInfo> = ArrayList<FoodInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_me_order)


        toolbar_order.setOnClickListener { onBackPressed() }

        initData()
        val layoutManager = LinearLayoutManager(this)
        order_recyclerView.layoutManager = layoutManager
        val adapter = FoodAdapter(foodList)
        order_recyclerView.adapter = adapter

    }

    private class FoodViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image : ImageView = view.findViewById(R.id.food_image)
        val name: TextView = view.findViewById(R.id.food_name)
        val state : TextView = view.findViewById(R.id.food_state)
        val price: TextView = view.findViewById(R.id.food_price)
        val number: TextView = view.findViewById(R.id.food_number)
    }

    private class FoodAdapter(val foodList:List<FoodInfo>): RecyclerView.Adapter<FoodViewHolder>(){
        override fun getItemCount(): Int {
            return foodList.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_order,parent,false)

            return FoodViewHolder(view)
        }

        override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
            val info = foodList[position]
            holder.image.setImageResource(info.food_image)
            holder.name.text = info.food_name
            holder.state.text = info.food_state
            holder.price.text = info.food_price
            holder.number.text = info.food_number
        }
    }

    private fun initData(){
        foodList.add(FoodInfo(R.drawable.icon_food_1, "糖醋里脊","运送中","￥25.4","x1"))
        foodList.add(FoodInfo(R.drawable.icon_food_2, "蛋黄南瓜","已送达","￥18.6","x1"))
    }
}