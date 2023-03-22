package com.example.myapplication.sqlite

import android.provider.BaseColumns

object MyDatabaseContract {
    // 定义表名
    const val TABLE_NAME = "vecentek"

    // 定义各个列名
    object Columns {
        const val COLUMN_NAME_USERID = "userid"
        const val COLUMN_NAME_VIN = "vin"
        const val COLUMN_NAME_CONTENT = "content"
        const val COLUMN_NAME_DONE = "done"
    }

    // 创建表的SQL语句
    const val SQL_CREATE_TABLE =
        "CREATE TABLE $TABLE_NAME (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${Columns.COLUMN_NAME_USERID} TEXT," +
                "${Columns.COLUMN_NAME_VIN} TEXT," +
                "${Columns.COLUMN_NAME_CONTENT} TEXT," +
                "${Columns.COLUMN_NAME_DONE} INTEGER)"

    // 删除表的SQL语句
    const val SQL_DELETE_TABLE =
        "DROP TABLE IF EXISTS $TABLE_NAME"
}
