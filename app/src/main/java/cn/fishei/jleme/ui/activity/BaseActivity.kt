package cn.fishei.jleme.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.fishei.jleme.util.ActivityCollector
import com.gyf.immersionbar.ktx.immersionBar

// 用来管理所有继承的Activity，将活动的Activity放到数组中
open class BaseActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCollector.addActivity(this)

        immersionBar {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }


}