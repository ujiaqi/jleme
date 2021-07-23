package cn.fishei.jleme.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import cn.fishei.jleme.ui.adapter.AddressAdapter
import cn.fishei.jleme.R
import cn.fishei.jleme.center.LoginData
import cn.fishei.jleme.logic.model.AddressList
import kotlinx.android.synthetic.main.activity_me_address.*



//
class MeAddressActivity : BaseActivity() {
    private val addressList = ArrayList<AddressList>()
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_me_address)

        toolbar.setOnClickListener { onBackPressed() }

        val layoutManager = LinearLayoutManager(this)
        address_recycler.layoutManager = layoutManager
        val adapter = AddressAdapter(addressList)
        address_recycler.adapter = adapter

        val getData  = getSharedPreferences("addressInfo", MODE_PRIVATE)
        Log.d("size",LoginData.size.toString())
        for (a in 0 until LoginData.size){
            val address = getData.getString("address_${a}","null").toString()
            val name = getData.getString("name_${a}","null").toString()
            val phone  = getData.getString("phone_${a}","null").toString()
            Log.d("address",address)
            initData(address,name,phone)
        }

    }

    private fun initData(address:String, name: String, phone:String) {
        addressList.add(AddressList(address, name, phone))
    }
}
