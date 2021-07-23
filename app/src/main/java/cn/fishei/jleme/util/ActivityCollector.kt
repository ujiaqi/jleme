package cn.fishei.jleme.util

import android.app.Activity

import java.util.*


class ActivityCollector {
    companion object{
        //记录打开了多少个Activity
        var activities: MutableList<Activity> = ArrayList()

        fun addActivity(activity: Activity) {
            activities.add(activity)
        }

        fun removeActivity(activity: Activity){
            activities.remove(activity)
        }
        //遍历数组，将所有数组中的activity Finish掉，然后再移出数组
        fun finishAll() {
            for (activity: Activity in activities) {
                if (!activity.isFinishing) {
                    activity.finish()
                }
            }
            //完全杀死进程，用户体验不好
//            exitProcess(0)
        }
    }


}