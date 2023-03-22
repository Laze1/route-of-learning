package com.example.myapplication.sqlite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySqliteBinding
import com.example.myapplication.ext.inflate
import com.example.myapplication.ext.onClick

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
                insert()
            }
            delete.onClick {

            }
            update.onClick {

            }
            query.onClick {
                query()
            }
        }
    }

    private fun insert() {
        binding.countView.text = if (ULogDB.insert(
                ULogBean(
                    userid = count.toString(),
                    vin = "VIN",
                    content = "ABCDEc",
                    done = 0
                )
            )
        ) "true" else "false"
    }

    private fun query() {
        val list = ULogDB.query()
        val builder = StringBuffer()
        list.forEach {
            builder.append("${it.id},${it.userid},${it.vin},${it.content},${it.done}\n")
        }
        binding.show.text = builder.toString()
    }
}