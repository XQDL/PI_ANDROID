package com.pi.dahora.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.pi.dahora.Models.AuthenticateDTO
import com.pi.dahora.Models.EndpointAuthenticate
import com.pi.dahora.Models.User
import com.pi.dahora.Utils.NetworkUtils
import com.pi.dahora.coordinator.HomeCoordinatorActivity
import com.pi.dahora.databinding.ActivityLoginBinding
import com.pi.dahora.studant.HomeStudantActivity
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
            if((email == "") and (password == "")){
                getData("123","123")
            }
            else{
                getData(email, password)
            }

        }
    }

    private fun callHomeStudent(user : User){
        val userGson = Gson().toJson(user).toString()
        Intent(this, HomeStudantActivity::class.java).apply {
            this.putExtra("aluno",userGson)
            startActivity(this)
        }
    }

    private fun callHomeCoordinator(){
        Intent(this, HomeCoordinatorActivity::class.java).apply {
            startActivity(this)
        }
    }

    //Variavel global que pode ser acessada de qualquer lugar
    companion object{
        lateinit var userLogged : User
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
                val user = response.body()

                //binding.errorTv.text = "NOME:" + response.body()?.name + "\nEMAIL:" + response.body()?.email + "\nSENHA:" + response.body()?.password
                binding.errorTv.text = user?.toString()

                if (user != null) {
                    userLogged = user
                    //callHomeStudent(user)
                    callHomeCoordinator()
                }
            }
        })
    }
}