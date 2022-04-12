package com.pi.dahora

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pi.dahora.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
    }

    fun initView(){
        binding.loginBtn.setOnClickListener {
            finish()
            Intent(this, HomeStudant::class.java).apply {
                startActivity(this)
            }
        }
    }
}