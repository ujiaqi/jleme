package cn.fishei.jleme.util

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import android.widget.Button
import androidx.core.graphics.drawable.toDrawable
import kotlinx.android.synthetic.main.activity_login.*


/**
 * Created by kqw on 2016/5/11.
 * TimeCountUtil
 */
class TimeCountUtil(private val mButton: Button, millisInFuture: Long, countDownInterval: Long) :
    CountDownTimer(millisInFuture, countDownInterval) {
    override fun onTick(millisUntilFinished: Long) {
        // 按钮不可用
        mButton.isEnabled = false
        val showText: String = (millisUntilFinished/1000).toString()
        mButton.text = showText

    }

    override fun onFinish() {
        // 按钮设置可用
        mButton.isEnabled = true
        mButton.text = "重新获取"
//        mButton.setBackgroundColor(Color.parseColor("#51DBFD"))

    }
}