package com.pi.dahora

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pi.dahora.Models.Cordinator
import com.pi.dahora.Models.Endpoint
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
        getData()
    }

    fun initView(){
        binding.loginBtn.setOnClickListener {
            finish()
            Intent(this, HomeStudant::class.java).apply {
                startActivity(this)
            }
        }
    }

    fun getData() {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://apidahora.herokuapp.com")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object : Callback<List<Cordinator>> {
            override fun onFailure(call: Call<List<Cordinator>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Cordinator>>, response: Response<List<Cordinator>>) {
                response.body()?.forEach {
                    binding.name.hint= it.name
                    //textView.text = textView.text.toString().plus(it.body)
                }
            }
        })

    }
}