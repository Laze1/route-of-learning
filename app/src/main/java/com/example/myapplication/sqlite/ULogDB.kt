package com.example.myapplication.sqlite

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns

object ULogDB {

    var dbHelper: FeedReaderDbHelper? = null

    fun init(context:Context) {
        dbHelper = FeedReaderDbHelper(context)
    }

    /**
    * @return true 成功
    * */
    fun insert(uLog:ULogBean):Boolean{
        // Gets the data repository in write mode
        val db = dbHelper?.writableDatabase
        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(MyDatabaseContract.Columns.COLUMN_NAME_USERID, uLog.userid)
            put(MyDatabaseContract.Columns.COLUMN_NAME_VIN, uLog.vin)
            put(MyDatabaseContract.Columns.COLUMN_NAME_CONTENT, uLog.content)
            put(MyDatabaseContract.Columns.COLUMN_NAME_DONE, 0)
        }

        // Insert the new row, returning the primary key value of the new row
        val insert = db?.insert(MyDatabaseContract.TABLE_NAME, null, values)
        if (insert != -1L){
            return true
        }
        return false
    }

    fun query():MutableList<ULogBean>{
        val db = dbHelper?.readableDatabase
        val cursor =
            db?.rawQuery("SELECT * FROM vecentek WHERE done != 1 ORDER BY _id LIMIT 50", arrayOf())
        val item = mutableListOf<ULogBean>()
        cursor?.use {
            with(cursor) {
                while (moveToNext()) {
                    val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                    val userid = getString(getColumnIndexOrThrow(MyDatabaseContract.Columns.COLUMN_NAME_USERID))
                    val vin = getString(getColumnIndexOrThrow(MyDatabaseContract.Columns.COLUMN_NAME_VIN))
                    val content = getString(getColumnIndexOrThrow(MyDatabaseContract.Columns.COLUMN_NAME_CONTENT))
                    val done = getInt(getColumnIndexOrThrow(MyDatabaseContract.Columns.COLUMN_NAME_DONE))
                    item.add(ULogBean(id, userid, vin, content, done))
                }
            }
        }
        return item
    }
}