package cn.fishei.jleme.ui.activity

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.fishei.jleme.JilemeApplication
import cn.fishei.jleme.JilemeApplication.Companion.context
import cn.fishei.jleme.MainActivity
import cn.fishei.jleme.R
import cn.fishei.jleme.center.LoginData
import cn.fishei.jleme.ui.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_me.*
import kotlinx.android.synthetic.main.fragment_index.*

class MeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_me)
    }



}