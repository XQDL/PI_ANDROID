package com.pi.dahora.studant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.pi.dahora.R

class HomeStudantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dado = intent.getSerializableExtra("aluno")
        setContentView(R.layout.activity_home_studant)
        findViewById<TextView>(R.id.text_xqdl).text = dado.toString()
    }
}