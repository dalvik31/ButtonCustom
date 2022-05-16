package com.dalvik.custombuttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dalvik.custombutton.CustomButton

class MainActivity : AppCompatActivity() {
    private var buttonCustom: CustomButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonCustom = findViewById(R.id.customButton)

        buttonCustom!!.setOnClickListener {
            Toast.makeText(this, "button clicked", Toast.LENGTH_LONG).show()
        }

    }

}