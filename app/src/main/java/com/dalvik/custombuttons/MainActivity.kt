package com.dalvik.custombuttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dalvik.custombutton.CustomButton

class MainActivity : AppCompatActivity() {
    private var buttonred: CustomButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonred = findViewById(R.id.customButton)

        buttonred!!.setOnClickListener {
            buttonred!!.setLoading(true)
            Toast.makeText(this, "button clicked", Toast.LENGTH_LONG).show()
        }

    }

}