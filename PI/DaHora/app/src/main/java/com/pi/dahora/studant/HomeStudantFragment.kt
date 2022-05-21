package com.pi.dahora.studant

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pi.dahora.Models.*
import com.pi.dahora.R
import com.pi.dahora.databinding.FragmentHomeStudantBinding
import com.pi.dahora.utils.LoginUser
import com.pi.dahora.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class HomeStudantFragment : Fragment() {
    private lateinit var binding: FragmentHomeStudantBinding
    private lateinit var course: Course


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeStudantBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }


    private fun getData() {

        binding.tvHours.textSize = 24F
        binding.tvHours.text = "Carregando..."
        binding.tvHours.setTextColor(resources.getColor(R.color.green_200))




        val retrofitClient = NetworkUtils.getRetrofitInstance("https://apidahora.herokuapp.com/api/")
        val endpoint = retrofitClient.create(EndpointCourse::class.java)

        val courseId = LoginUser.userLogged.courseId

        val callback = endpoint.getCourseById(courseId!!)

        callback.enqueue(object : Callback<Course> {
            override fun onFailure(call: Call<Course>, t: Throwable) {
                binding.tvHours.setTextColor(Color.RED)
                binding.tvHours.text = "Desculpe, ocorreu um erro interno no servidor!"
            }

            override fun onResponse(call: Call<Course>, response: Response<Course>) {
                val erroMensage = when(response.raw().code()){
                    404 -> "E-mail ou senha inválidos"
                    500 -> "Desculpe, ocorreu um erro interno no servidor!"
                    else -> "Ops, ocorreu um erro inesperado!"
                }
                showError(erroMensage)


                try{
                    course = response.body()!!
                    hourRender()
                } catch(e : Exception){
                    showError("Problema ao encontrar suas horas complementares!!")
                }
            }
        })
    }

    private fun hourRender() {
        binding.tvHours.textSize = 38F
        binding.tvHours.setTextColor(Color.GREEN)

        var performed = formatHour(LoginUser.userLogged.additionalHoursPerformed.toString())
        var target = formatHour(course.additionalHoursTarget.toString())

        binding.tvHours.text =  performed + "/" + target + " Hrs"
    }

    private fun formatHour(hour: String): String {
        var splited = hour.split(".")
        var formattedHour : String

        if(splited[1] == "0"){
            formattedHour = splited[0]
        } else{
            formattedHour = hour
        }

        return formattedHour
    }


    private fun showError(msg: String){
        binding.tvHours.setTextColor(Color.RED)
        binding.tvHours.text = msg
    }



}
