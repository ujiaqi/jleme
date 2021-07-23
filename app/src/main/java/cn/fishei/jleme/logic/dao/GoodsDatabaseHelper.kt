package cn.fishei.jleme.logic.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import cn.fishei.jleme.ui.fragment.MenuFragment

//创建SQLite数据库，这边珠宝保存一些餐单的信息进行持久化存储，参考P289
class GoodsDatabaseHelper(val context: Context, name: String, version: Int): SQLiteOpenHelper(context, name, null, version) {

    private val createGoods = "create table Goods ( "+
            "id integer primary key autoincrement, "+
            "name text, price text, pic text, tags text, sales text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createGoods)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists Goods")
        onCreate(db)
    }


}