package com.pi.dahora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.login_btn).setOnClickListener{ x() }
    }
    private fun x(){
        setContentView(R.layout.activity_requirement_history_studant)
    }


}