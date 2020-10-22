package com.example.memo.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memo.R
import kotlinx.android.synthetic.main.activity_write.*

class WriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        save_button.setOnClickListener {
            val intent = Intent()
            intent.putExtra("title", title_editText.text.toString())
            intent.putExtra("subtitle", subtitle_editText.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}