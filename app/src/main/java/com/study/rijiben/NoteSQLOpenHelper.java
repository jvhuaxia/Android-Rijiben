package com.study.rijiben;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 创建SQLite数据库的Helper类
 */
public class NoteSQLOpenHelper extends SQLiteOpenHelper {

    /**
     *  这个只需要一个表 所以只接收一个Context 库名在构造函数里填好了
     * @param context 应用的Context
     */
    public NoteSQLOpenHelper(Context context) {
        super(context, "riji", null, 1);
    }

    /**
     *  创建数据库执行的东西
     *  在这里撞创建了 riji 表
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE riji(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(0),content VARCHAR(0),time VARCHAR(0),tianqi VARCHAR(0),xinqing VARCHAR(0),lasttime VARCHAR(0));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

}
