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
import cn.fishei.jleme.R
import kotlinx.android.synthetic.main.activity_me_red_bag.*

class MeRedBagActivity : BaseActivity() {
    private val bagList:ArrayList<BagInfo> = ArrayList<BagInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_me_red_bag)

        toolbar_RedBag.setOnClickListener { onBackPressed() }

        initData()
        val layoutManager = LinearLayoutManager(this)
        redBag_recycler.layoutManager = layoutManager
        val adapter = BagAdapter(bagList)
        redBag_recycler.adapter = adapter

    }


        private class BagViewHolder(view: View): RecyclerView.ViewHolder(view){
            val image : ImageView = view.findViewById(R.id.image1)
            val bigTitle: TextView = view.findViewById(R.id.big_title)
            val time : TextView = view.findViewById(R.id.time)
            val price: TextView = view.findViewById(R.id.price)
            val text: TextView = view.findViewById(R.id.test_two)
        }

        private class BagAdapter(val bagList:List<BagInfo>): RecyclerView.Adapter<BagViewHolder>(){
            override fun getItemCount(): Int {
                return bagList.size
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_redbag,parent,false)

                return BagViewHolder(view)
            }

            override fun onBindViewHolder(holder: BagViewHolder, position: Int) {
                val info = bagList[position]
                holder.image.setImageResource(info.imageData)
                holder.bigTitle.text = info.bigTitle
                holder.time.text = info.Time
                holder.price.text = info.price
                holder.text.text = info.text
            }
        }

    private fun initData(){
        bagList.add(BagInfo(R.drawable.dingdan, "通用红包","2020.12.30","25","满50使用"))
        bagList.add(BagInfo(R.drawable.dingdan, "通用红包","2020.12.30","5","满50使用"))
    }

}