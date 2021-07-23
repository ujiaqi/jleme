package cn.fishei.jleme


import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import cn.fishei.jleme.ui.activity.BaseActivity
import cn.fishei.jleme.ui.fragment.IndexFragment
import cn.fishei.jleme.ui.fragment.MeFragment
import cn.fishei.jleme.ui.fragment.MenuFragment
import cn.fishei.jleme.util.ActivityCollector
import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.tencent.android.tpush.XGIOperateCallback
//import com.tencent.android.tpush.XGPushConfig
//import com.tencent.android.tpush.XGPushManager
import java.util.*


class MainActivity : BaseActivity() {

    private lateinit var indexFrag: Fragment
    private lateinit var menuFrag: Fragment
    private lateinit var meFrag: Fragment
    private lateinit var bottomNav: BottomNavigationView
    //    private lateinit var fragShoppingCart:Fragment
//    private lateinit var fragMe:Fragment
    private lateinit var fragments: Array<Fragment>

    //用于记录上个选择的Fragment
    companion object{
        var lastFragment = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        requestPermission()
        Log.d("TAG", "hello")
//        XGPushConfig.enableDebug(this, true)
//
//        //消息推送
//        XGPushManager.registerPush(this, object : XGIOperateCallback {
//            override fun onSuccess(data: Any, flag: Int) {
//                //token在设备卸载重装的时候有可能会变
//                Log.d("TPush", "注册成功，设备token为：$data")
//            }
//
//            override fun onFail(data: Any, errCode: Int, msg: String) {
//                Log.d("TPush", "注册失败，错误码：$errCode,错误信息：$msg")
//            }
//        })

    }

    //退出软件
    override fun onBackPressed() {
//        super.onBackPressed()
        AlertDialog.Builder(this).setTitle("肚子空空哦~")
            .setMessage("这么多可口的美食，饥不可耐T-T")
            .setPositiveButton("溜一会儿", DialogInterface.OnClickListener { _, _ ->
                ActivityCollector.finishAll()
            }).setNegativeButton("朕手滑了", null).show()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initFragment() {
        bottomNav = findViewById(R.id.main_bottom_nav)
        indexFrag = IndexFragment()
        menuFrag = MenuFragment()
        meFrag = MeFragment()
        fragments = arrayOf(indexFrag, menuFrag, meFrag)

        //底部选择bar
        when (lastFragment) {
            0 -> supportFragmentManager.beginTransaction()
                .replace(R.id.main_above_container, indexFrag).show(indexFrag).commit()

            1 -> supportFragmentManager.beginTransaction()
                .replace(R.id.main_above_container, menuFrag).show(menuFrag).commit()

            2 -> supportFragmentManager.beginTransaction()
                .replace(R.id.main_above_container, meFrag).show(meFrag).commit()

        }

        bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_main -> {
                    if (lastFragment != 0) {
                        switchFragment(lastFragment, 0)
                        lastFragment = 0
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_menu -> {
                    if (lastFragment != 1) {
                        switchFragment(lastFragment, 1)
                        lastFragment = 1
                    }
                    return@OnNavigationItemSelectedListener true
                }

                R.id.bottom_me -> {
                    if (lastFragment != 2) {
                        switchFragment(lastFragment, 2)
                        lastFragment = 2
                    }
                    return@OnNavigationItemSelectedListener true
                }

            }
            true
        })
        bottomNav.setOnNavigationItemReselectedListener {
            Log.d("TAG", "1")
            when(it.itemId){
                R.id.bottom_main -> {
                    if (lastFragment != 0) {
                        switchFragment(lastFragment, 0)
                        lastFragment = 0
                    }

                }
                R.id.bottom_menu -> {
                    if (lastFragment != 1) {
                        switchFragment(lastFragment, 1)
                        lastFragment = 1
                    }

                }
                R.id.bottom_me -> {
                    if (lastFragment != 2) {
                        switchFragment(lastFragment, 2)
                        lastFragment = 2
                    }

                }
            }


        }

    }
    private fun switchFragment(lastIndex: Int, index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.hide(fragments[lastIndex])
        if (!fragments[index].isAdded) {
            transaction.add(R.id.main_above_container, fragments[index])
        }
        transaction.show(fragments[index]).commitAllowingStateLoss()
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            val permissionsList = ArrayList<String>()
            val permissions = arrayOf(
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE)
            for (perm in permissions) {
                if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(perm)) {
                    permissionsList.add(perm)
                    // 进入到这里代表没有权限.
                }
            }
            if (!permissionsList.isEmpty()) {
                val strings = arrayOfNulls<String>(permissionsList.size)
                requestPermissions(permissionsList.toArray(strings), 0)
            }
        }
    }


}