package com.pi.dahora

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pi.dahora.Models.*
import com.pi.dahora.Utils.NetworkUtils
import com.pi.dahora.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()





    }

    private fun initView(){

        binding.loginBtn.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            getData(email, password)
        }
    }
    private fun callHomeStudent(){
        Intent(this, HomeStudant::class.java).apply {
            startActivity(this)
        }
    }
    private fun CallHomeCoordinator(){
        Intent(this, HomeStudant::class.java).apply {
            startActivity(this)
        }
    }



    private fun getData(email: String, password: String) {

        binding.errorTv.visibility = View.VISIBLE
        binding.errorTv.text = "AUTENTICANDO..."

        val retrofitClient = NetworkUtils.getRetrofitInstance("https://apidahora.herokuapp.com/api/")

        val endpoint = retrofitClient.create(EndpointAuthenticate::class.java)

        val auth = AuthenticateDTO(email, password)

        val callback = endpoint.authenticate(auth)

        callback.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                binding.errorTv.text = "Falha de Login! Tente novamente!"
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {

                //binding.errorTv.text = "NOME:" + response.body()?.name + "\nEMAIL:" + response.body()?.email + "\nSENHA:" + response.body()?.password
                binding.errorTv.text = response.body()?.toString()

                Handler().postDelayed({
                    callHomeStudent()
                },3000)
                callHomeStudent()

                //textView.text = textView.text.toString().plus(it.bod
            // y)
            }
        })

    }
}