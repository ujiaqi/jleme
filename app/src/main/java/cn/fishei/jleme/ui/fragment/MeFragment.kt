package cn.fishei.jleme.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.fishei.jleme.JilemeApplication
import cn.fishei.jleme.R
import cn.fishei.jleme.center.LoginData
import cn.fishei.jleme.ui.activity.*
import cn.fishei.jleme.ui.viewModel.UserViewModel
import cn.fishei.jleme.util.ConfigData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_me.*
import kotlinx.android.synthetic.main.activity_me_address.*
import kotlinx.android.synthetic.main.address_recycler.*


class MeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_me, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel by lazy { ViewModelProvider(this).get(UserViewModel::class.java) }
        if (LoginData.LOGIN_STATUS == 0){
            login_out_card.visibility = View.GONE

        }

        val isNight: Boolean = ConfigData.getIsNight()
        //设置白夜间模式
        switch_night.isChecked = isNight
        switch_night.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                ConfigData.setIsNight(true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                ConfigData.setIsNight(false)
            }

            activity!!.recreate()
        }

        //账号安全
        layout_me_pra.setOnClickListener{
            val intent = Intent(JilemeApplication.context, MeAsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        //隐私
        layout_privacy.setOnClickListener{
            /*val intent = Intent(JilemeApplication.context, MePrivacyActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)*/
            Toast.makeText(context,"别点我，没结果！",Toast.LENGTH_SHORT).show()
        }
        
        //我的地址
        layout_address.setOnClickListener{
             if (LoginData.LOGIN_STATUS == 1 ) {
                 if (LoginData.GET_ADDRESS == 0){
                     //获取资源
                     val Token = activity?.getSharedPreferences("userInfo",
                         AppCompatActivity.MODE_PRIVATE)
                     val token = Token?.getString("token", "null")
                     Log.d("token", token.toString())
                     if (token != null) {
                         viewModel.resultLocation(token)
                     }

                     viewModel.locationData.observe(viewLifecycleOwner, Observer { result ->
                         val info = result.getOrNull()
                         LoginData.size = info?.result?.size!!
                         if ("ok" == info.code) {
                             for (i in 0 until LoginData.size) {
                                 //本地存储我的地址
                                 val userInfo = activity?.getSharedPreferences("addressInfo",
                                     AppCompatActivity.MODE_APPEND)?.edit()
                                 userInfo?.putString("address_${i}",
                                     info.result[i].province + info.result[i].city + info.result[i].district + info.result[i].detailAddr)
                                 userInfo?.putString("name_${i}", info.result[i].name)
                                 userInfo?.putString("phone_${i}", info.result[i].phone)
                                 userInfo?.apply()
                             }
                             LoginData.GET_ADDRESS = 1
                             val intent = Intent(JilemeApplication.context,
                                 MeAddressActivity::class.java)
                             startActivity(intent)
                         } else {
                             Toast.makeText(context, "获取地址失败！", Toast.LENGTH_SHORT).show()

                         }
                     })

                 }else{
                     val intent = Intent(JilemeApplication.context, MeAddressActivity::class.java)
                     startActivity(intent)
                 }
             }else{
                 Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show()
             }

        }
        //意见反馈
        layout_opinion.setOnClickListener{
            val intent = Intent(JilemeApplication.context, MeOpinionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        //红包
        layout_me_redBag.setOnClickListener{
            val intent = Intent(JilemeApplication.context, MeRedBagActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        //个人中心
        card_me_data.setOnClickListener{

                if (LoginData.LOGIN_STATUS == 0){
                    val intent = Intent(JilemeApplication.context, LoginActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(context, "已经登录", Toast.LENGTH_SHORT).show()
                }

        }
        //关于
        layout_me_about.setOnClickListener{
            val intent = Intent(JilemeApplication.context, MeAboutActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        //订单
        layout_me_order.setOnClickListener{
            val intent = Intent(JilemeApplication.context, MeOrderActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        //评价中心
        layout_me_word_list.setOnClickListener{
            val intent = Intent(JilemeApplication.context, MeEvaluateActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        login_out.setOnClickListener{
            val pers = activity?.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
            pers?.edit()?.clear()?.apply()
            login_out_card.visibility = View.GONE
            LoginData.LOGIN_STATUS = 0
            LoginData.GET_ADDRESS = 0
            val intent = Intent(JilemeApplication.context, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        LoginData.TURN_MAIN = 0
        if (LoginData.LOGIN_STATUS == 1){
            val pers = activity?.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
            text_me_words.text = pers?.getString("userName", "未登录")
            val photo = pers?.getString("userPhoto", "")
            Glide.with(JilemeApplication.context).load(photo).into(userPhoto)
            login_out_card.visibility = View.VISIBLE
        }else{

            Glide.with(JilemeApplication.context).load(R.drawable.touxiang).into(userPhoto)
            text_me_words.text = "未登录"
            login_out_card.visibility = View.GONE
        }
    }

}