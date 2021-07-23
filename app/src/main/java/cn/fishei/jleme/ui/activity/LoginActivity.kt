package cn.fishei.jleme.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import cn.fishei.jleme.JilemeApplication
import cn.fishei.jleme.JilemeApplication.Companion.context
import cn.fishei.jleme.R
import cn.fishei.jleme.center.LoginData
import cn.fishei.jleme.logic.network.UserNetwork
import cn.fishei.jleme.ui.fragment.MeFragment

import cn.fishei.jleme.ui.viewModel.UserViewModel
import cn.fishei.jleme.util.TimeCountUtil
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_me.*
import kotlin.math.log


class LoginActivity : BaseActivity() {
    private val viewModel by lazy {ViewModelProvider(this).get(UserViewModel::class.java)}

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //返回
        login_delete.setOnClickListener {
            onBackPressed()
        }

        //转化为密码登录页面
        login_turn_password.setOnClickListener {
            val intent = Intent(context, LoginPasswordActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }

        //发送验证码
        send_code.setOnClickListener {
            val phone = enter_phone.text.toString()
            LoginData.SAVE_COOKIE = 1
            viewModel.resultStatus(phone)
            viewModel.backInfo.observe(this, Observer{ result ->
                val info: String? = result.getOrNull()
                val mTimeCountUtil = TimeCountUtil(send_code, 60000, 1000)
                if("ok" == info){
                    mTimeCountUtil.start()
                    Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show()
                    val getCookie = getSharedPreferences("cookie", Context.MODE_PRIVATE).edit()
                    getCookie.putString("getCookie",UserNetwork.cookie)
                    Toast.makeText(this,UserNetwork.cookie,Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show()
                }
//
            })

        }



        //登录
        loginBtn.setOnClickListener {
            val code = enter_code.text.toString()
            val phone = enter_phone.text.toString()
            Toast.makeText(this,UserNetwork.cookie,Toast.LENGTH_SHORT).show()
            val pers = getSharedPreferences("cookie", Context.MODE_PRIVATE)
            Log.d("cookie test",pers.getString("getCookie","").toString())

            viewModel.resultPhoneCode(phone, code, UserNetwork.cookie.toString())
            viewModel.phoneLiveData.observe(this, Observer { result ->
                val info= result.getOrNull()
                if ("ok" == info?.code){
                    //Toast.makeText(this, "头像"+info.detailUser.user.userPhoto, Toast.LENGTH_SHORT).show()
                    // 存入到本地文件中
                    val userInfo = getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit()
                    userInfo.putString("userName",info.detailUser.user.username)
                    userInfo.putString("userPhoto",info.detailUser.user.userPhoto)
                    userInfo.putString("phone",info.detailUser.user.phone)
                    userInfo.putString("token",info.detailUser.token)
                    userInfo.apply()
                    Log.d("codeToken",info.detailUser.token)
                    LoginData.LOGIN_STATUS = 1
                    onBackPressed()
                }else{
                    Toast.makeText(this, "验证码无效！", Toast.LENGTH_SHORT).show()
                }
            })
        }


    }

    override fun onResume() {
        super.onResume()
        //再次后退，到主页面，否则还是在验证码登录页
        if(LoginData.TURN_MAIN == 1){
            onBackPressed()
        }
    }


}