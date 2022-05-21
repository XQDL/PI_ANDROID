package com.pi.dahora.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pi.dahora.Models.AuthenticateDTO
import com.pi.dahora.Models.EndpointAuthenticate
import com.pi.dahora.Models.User
import com.pi.dahora.R
import com.pi.dahora.utils.NetworkUtils
import com.pi.dahora.coordinator.HomeCoordinatorActivity
import com.pi.dahora.databinding.ActivityLoginBinding
import com.pi.dahora.studant.HomeStudantActivity
import com.pi.dahora.utils.LoginUser
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
        binding.ButtonLogin.performClick()
    }

    private fun initView(){
        binding.ButtonLogin.setOnClickListener {
            val email = binding.EditTextEmailLogin.text.toString()
            val password = binding.EditTextPasswordLogin.text.toString()
            if((email == "") and (password == "")){
                getData("321","321")

                //getData("carlos.gouveia@unifacear.com","12345678")
            }
            else{
                getData(email, password)
            }
        }
    }

    private fun callHomeStudent(){
        Intent(this, HomeStudantActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }

    private fun callHomeCoordinator(){
        Intent(this, HomeCoordinatorActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }


    private fun getData(email: String, password: String) {

        binding.TextViewErrorLogin.visibility = View.VISIBLE
        binding.TextViewErrorLogin.text = "AUTENTICANDO..."
        binding.TextViewErrorLogin.setTextColor(resources.getColor(R.color.green_200))
        binding.ProgressBarLogin.visibility = View.VISIBLE

        val matricula = null

        val retrofitClient = NetworkUtils.getRetrofitInstance("https://apidahora.herokuapp.com/api/")
        val endpoint = retrofitClient.create(EndpointAuthenticate::class.java)
        val auth = AuthenticateDTO(email, password, matricula)
        val callback = endpoint.authenticate(auth)

        callback.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
               showError("Desculpe, ocorreu um erro interno no servidor!")
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {

                val erroMensage = when(response.raw().code()){
                    200 -> ""
                    404 -> "E-mail ou senha inválidos"
                    500 -> "Desculpe, ocorreu um erro interno no servidor!"
                    else -> "Ops, ocorreu um erro inesperado!"
                }

                showError(erroMensage)

                val user = response.body()

                if (user != null) {
                    callHome(user)
                }
                else{
                    binding.TextViewErrorLogin.text = "Falha de Login! Tente novamente!"
                }
            }
        })
    }

    private fun callHome(user: User){

        LoginUser.userLogged = user

        if (user.phoneNumber != null){
            callHomeCoordinator()
        }
        else {
            callHomeStudent()
        }
    }

    private fun showError(msg: String){
        binding.TextViewErrorLogin.setTextColor(Color.RED)
        binding.ProgressBarLogin.visibility = View.GONE
        binding.TextViewErrorLogin.text = msg
    }
}