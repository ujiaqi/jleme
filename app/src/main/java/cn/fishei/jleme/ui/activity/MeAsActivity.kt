package cn.fishei.jleme.ui.activity

import android.os.Bundle
import cn.fishei.jleme.R
import kotlinx.android.synthetic.main.activity_me_as.*

class MeAsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_me_as)

        toolbar.setOnClickListener{onBackPressed()}
    }
}