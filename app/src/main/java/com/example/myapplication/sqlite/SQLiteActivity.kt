package com.example.myapplication.sqlite

import android.content.ContentValues
import android.os.Bundle
import android.provider.BaseColumns
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySqliteBinding
import com.example.myapplication.ext.inflate
import com.example.myapplication.ext.onClick
import com.example.myapplication.sqlite.MyDatabaseContract.Columns.COLUMN_NAME_CONTENT
import com.example.myapplication.sqlite.MyDatabaseContract.Columns.COLUMN_NAME_DONE
import com.example.myapplication.sqlite.MyDatabaseContract.Columns.COLUMN_NAME_USERID
import com.example.myapplication.sqlite.MyDatabaseContract.Columns.COLUMN_NAME_VIN

class SQLiteActivity : AppCompatActivity() {

    private val binding: ActivitySqliteBinding by inflate()

    val dbHelper by lazy { FeedReaderDbHelper(this@SQLiteActivity) }

    var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
        binding.apply {
            countView.text = count.toString()
            add.onClick {
                // Gets the data repository in write mode
                val db = dbHelper.writableDatabase

                // Create a new map of values, where column names are the keys
                val values = ContentValues().apply {
                    put(COLUMN_NAME_USERID, "$count")
                    put(COLUMN_NAME_VIN, "QWERT")
                    put(COLUMN_NAME_CONTENT, "ABCDE")
                    put(COLUMN_NAME_DONE, 0)
                }

                // Insert the new row, returning the primary key value of the new row
                val newRowId = db.insert(MyDatabaseContract.TABLE_NAME, null, values)
                if (newRowId != -1L){
                    count++
                }
                countView.text = count.toString()
            }
            delete.onClick {

            }
            update.onClick {

            }
            query.onClick {
                val db = dbHelper.readableDatabase

                // Define a projection that specifies which columns from the database
                // you will actually use after this query.
//                val projection = arrayOf(
//                    BaseColumns._ID,
//                    COLUMN_NAME_USERID,
//                    COLUMN_NAME_VIN,
//                    COLUMN_NAME_VIN,
//                    COLUMN_NAME_VIN,
//                )

                // Filter results WHERE "title" = 'My Title'
//                val selection = "$COLUMN_NAME_DONE != 1"
//                val selectionArgs = arrayOf(1)

                // How you want the results sorted in the resulting Cursor
//                val sortOrder = "${BaseColumns._ID}"

//                val cursor = db.query(
//                    TABLE_NAME,   // The table to query
//                    null,             // The array of columns to return (pass null to get all)
//                    selection,              // The columns for the WHERE clause
//                    null,          // The values for the WHERE clause
//                    null,                   // don't group the rows
//                    null,                   // don't filter by row groups
//                    sortOrder               // The sort order
//                )
                val cursor2 = db.rawQuery("SELECT * FROM vecentek WHERE done != 1 ORDER BY _id LIMIT 2", arrayOf())
                val item = mutableListOf<ULogBean>()
//                cursor.use { cursor ->
//                    with(cursor) {
//                        while (moveToNext()) {
//                            val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
//                            val userid = getString(getColumnIndexOrThrow(COLUMN_NAME_USERID))
//                            val vin = getString(getColumnIndexOrThrow(COLUMN_NAME_VIN))
//                            val content = getString(getColumnIndexOrThrow(COLUMN_NAME_CONTENT))
//                            val done = getInt(getColumnIndexOrThrow(COLUMN_NAME_DONE))
//                            item.add(ULogBean(id, userid, vin, content, done))
//                        }
//                    }
//                }
                cursor2.use { cursor ->
                    with(cursor) {
                        while (moveToNext()) {
                            val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                            val userid = getString(getColumnIndexOrThrow(COLUMN_NAME_USERID))
                            val vin = getString(getColumnIndexOrThrow(COLUMN_NAME_VIN))
                            val content = getString(getColumnIndexOrThrow(COLUMN_NAME_CONTENT))
                            val done = getInt(getColumnIndexOrThrow(COLUMN_NAME_DONE))
                            item.add(ULogBean(id, userid, vin, content, done))
                        }
                    }
                }

                val builder = StringBuffer()
                item.forEach {
                     builder.append("${it.id},${it.userid},${it.vin},${it.content},${it.done}\n")
                }
                binding.show.text = builder.toString()
            }
        }
    }
}