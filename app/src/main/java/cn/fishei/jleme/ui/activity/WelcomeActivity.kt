package cn.fishei.jleme.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.os.postDelayed
import cn.fishei.jleme.JilemeApplication
import cn.fishei.jleme.JilemeApplication.Companion.context
import cn.fishei.jleme.MainActivity
import cn.fishei.jleme.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlin.concurrent.thread


//启动欢迎页面
class WelcomeActivity : BaseActivity() {

    private lateinit var animation:ScaleAnimation

    lateinit var welcomeBg: ImageView
    private val setPic = 1
    @SuppressLint("HandlerLeak")
    private val handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                setPic -> Glide.with(context)
                    .load(R.drawable.wel_jleme)
                    .into(welcomeBg)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val welCard: CardView = findViewById(R.id.wel_card_word_1)
        val welText: TextView = findViewById(R.id.wel_card_text)
        welcomeBg =  findViewById(R.id.wel_bg_img)

        //设置字体
        val typeface = Typeface.createFromAsset(assets,"fonts/cuteFish.ttf")
        welText.typeface = typeface
        welText.text = "饥太美，味道美极了"

        welCard.background.alpha = 80
        animationConfig()

//



        thread {
            val msg = Message()
            msg.what = setPic
            handler.sendMessage(msg)
        }


        handler.postDelayed({
            welcomeBg.startAnimation(animation)
        }
        , 500)
    }

    //首页加载图片变化
    private fun animationConfig(){

        // 原图大小，不做变换
        animation = ScaleAnimation(1f, 1.1f, 1f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, 1, 0.5f)
        // 设置持续时间
        animation.duration = 3000
        // 设置动画结束之后的状态是否是动画的最终状态
        animation.fillAfter = true
        // 设置循环次数
        animation.repeatCount = 0
        // 设置动画结束后事件
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {

            }
            override fun onAnimationEnd(animation: Animation?) {
                val intent = Intent(JilemeApplication.context, MainActivity::class.java)
                startActivity(intent)
            }
            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
    }
}


