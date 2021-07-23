package cn.fishei.jleme.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.fishei.jleme.JilemeApplication
import cn.fishei.jleme.R
import cn.fishei.jleme.center.LoginData
import cn.fishei.jleme.logic.network.UserNetwork
import cn.fishei.jleme.ui.fragment.MeFragment
import cn.fishei.jleme.ui.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login_password.*
import kotlin.concurrent.thread

//密码登录页面
class LoginPasswordActivity : BaseActivity() {
    private val viewModel by lazy { ViewModelProvider(this).get(UserViewModel::class.java)}
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_password)

        login_password_delete.setOnClickListener {
            onBackPressed()
        }
        //转为验证码登录
        login_turn_phone.setOnClickListener {
                val intent = Intent(JilemeApplication.context, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
        }

        //点击登录
        agree_login.setOnClickListener {
            val phone = entered_phone.text.toString()
            val password = entered_password.text.toString()


            viewModel.resultPasswordLocation(phone, password)
            viewModel.passwordLogin.observe(this, Observer { result ->
                val info= result.getOrNull()
                if (1 == info?.user?.status){
                    //Toast.makeText(this, "头像"+info.user.user.userPhoto, Toast.LENGTH_SHORT).show()
                    // 用户信息存入到本地文件中
                    val pers = getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit()


                    pers.putString("userName", info.user.user.username)
                    pers.putString("userPhoto", info.user.user.userPhoto)
                    pers.putString("phone", info.user.user.phone)
                    pers.putString("token", info.user.token)
                    pers.apply()
                    Log.d("loginToken",info.user.token)

                    val getToken = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
                    val token = getToken.getString("token", "error")
                    Log.d("loginToken111",token.toString())
                    //设置登录成功状态为1
                    LoginData.LOGIN_STATUS = 1

                    LoginData.TURN_MAIN = 1
                    onBackPressed()


//                    val intent = Intent(JilemeApplication.context, MeActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    startActivity(intent)
                }else{
                    Toast.makeText(this, "密码无效！", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}