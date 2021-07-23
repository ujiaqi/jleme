package cn.fishei.jleme.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import cn.fishei.jleme.R
import cn.fishei.jleme.logic.dao.GoodsDatabaseHelper
import cn.fishei.jleme.ui.adapter.MenuAdapter
import cn.fishei.jleme.ui.viewModel.MenuViewModel
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : BaseActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(MenuViewModel::class.java)
    }
    private lateinit var adapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val dbHelper = GoodsDatabaseHelper(this, "Goods.db", 1)

        getMenuData("1")

        search_cancel.setOnClickListener { onBackPressed() }

        //设置显示商品
        category_out_pot.setOnClickListener {
            getMenuData("2")
            category_pot.setBackgroundColor(Color.parseColor("#ffffff"))
            category_homely.setBackgroundColor(Color.parseColor("#F2F3F8"))
            category_snack.setBackgroundColor(Color.parseColor("#F2F3F8"))
            category_drink.setBackgroundColor(Color.parseColor("#F2F3F8"))
        }

        category_homely.setOnClickListener {
            getMenuData("1")
            category_pot.setBackgroundColor(Color.parseColor("#F2F3F8"))
            category_homely.setBackgroundColor(Color.parseColor("#ffffff"))
            category_snack.setBackgroundColor(Color.parseColor("#F2F3F8"))
            category_drink.setBackgroundColor(Color.parseColor("#F2F3F8"))
        }

        category_snack.setOnClickListener {
            getMenuData("3")
            category_pot.setBackgroundColor(Color.parseColor("#F2F3F8"))
            category_homely.setBackgroundColor(Color.parseColor("#F2F3F8"))
            category_snack.setBackgroundColor(Color.parseColor("#ffffff"))
            category_drink.setBackgroundColor(Color.parseColor("#F2F3F8"))
        }

        category_drink.setOnClickListener {
            getMenuData("4")
            category_pot.setBackgroundColor(Color.parseColor("#F2F3F8"))
            category_homely.setBackgroundColor(Color.parseColor("#F2F3F8"))
            category_snack.setBackgroundColor(Color.parseColor("#F2F3F8"))
            category_drink.setBackgroundColor(Color.parseColor("#ffffff"))
        }

        search_edit_text.setOnKeyListener { v, keyCode, event ->
            category_pot.setBackgroundColor(Color.parseColor("#F2F3F8"))
            category_homely.setBackgroundColor(Color.parseColor("#F2F3F8"))
            category_snack.setBackgroundColor(Color.parseColor("#F2F3F8"))
            category_drink.setBackgroundColor(Color.parseColor("#F2F3F8"))
            Log.d("输出搜索",search_edit_text.text.toString())
            if (keyCode== KeyEvent.KEYCODE_ENTER){
                Log.d("点击搜索","开始搜索了")
                getSearchData(search_edit_text.text.toString())
            }
            return@setOnKeyListener false
        }

    }

    private fun getMenuData(id: String){
        val layoutManager = GridLayoutManager(this,2)
        menu_lists.layoutManager= layoutManager
        adapter = MenuAdapter(viewModel.goodsList)

        menu_lists.adapter = adapter

        category_homely.setBackgroundColor(Color.parseColor("#ffffff"))
        viewModel.resultMenu(id)
        viewModel.menuData.observe(this, Observer { result->
            val goods = result.getOrNull()
            if (goods != null) {
                viewModel.goodsList.clear()
                viewModel.goodsList.addAll(goods)
                adapter.notifyDataSetChanged()
            }else{
                viewModel.goodsList.clear()
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun getSearchData(key: String){
        val layoutManager = GridLayoutManager(this,2)
        menu_lists.layoutManager= layoutManager
        adapter = MenuAdapter(viewModel.goodsList)

        menu_lists.adapter = adapter

        viewModel.resultSearch(key)
        viewModel.searchGoods.observe(this, Observer { result->
            val goods = result.getOrNull()
            Log.d("搜索商品信息", goods?.get(0)?.name.toString())
            if (goods != null) {
                viewModel.goodsList.clear()
                viewModel.goodsList.addAll(goods)
                adapter.notifyDataSetChanged()
            }else{
                viewModel.goodsList.clear()
                adapter.notifyDataSetChanged()
            }
        })
    }
}