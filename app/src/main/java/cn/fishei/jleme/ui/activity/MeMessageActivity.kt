package cn.fishei.jleme.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.fishei.jleme.R
import kotlinx.android.synthetic.main.activity_me_message.*

class MeMessageActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_me_message)

        toolbar_myMessage.setOnClickListener { onBackPressed() }
    }
}