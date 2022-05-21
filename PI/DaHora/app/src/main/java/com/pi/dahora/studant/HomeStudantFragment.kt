package com.pi.dahora.studant

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pi.dahora.Models.Course
import com.pi.dahora.Models.EndpointCourse
import com.pi.dahora.R
import com.pi.dahora.databinding.FragmentHomeStudantBinding
import com.pi.dahora.utils.LoginUser
import com.pi.dahora.utils.NetworkUtils
import org.eazegraph.lib.models.PieModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class HomeStudantFragment : Fragment() {
    private lateinit var remain: String
    private lateinit var target: String
    private lateinit var performed: String
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
                    setChartData()
                } catch(e : Exception){
                    showError("Problema ao encontrar suas horas complementares!!")
                }
            }
        })
    }

    private fun hourRender() {
        binding.tvHours.textSize = 32F
        binding.tvHours.setTextColor(Color.BLACK)

        performed = formatHour(LoginUser.userLogged.additionalHoursPerformed.toString())
        target = formatHour(course.additionalHoursTarget.toString())
        remain = formatHour((target.toFloat() - performed.toFloat()).toString())


        binding.tvPerformedValue.text = performed + " Horas"
        binding.tvTargetValue.text = target + " Horas"
        binding.tvRemainValue.text = remain + " Horas"


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


    @SuppressLint("ClickableViewAccessibility")
    private fun setChartData() {
        // Set the data and color to the pie chart
        // Set the data and color to the pie chart

        val pieChart = binding.piechart
        pieChart.addPieSlice(
            PieModel(
                "Performed", target.toFloat() - performed.toFloat(),
                Color.parseColor("#FF0000")
            )
        )
        pieChart.addPieSlice(
            PieModel(
                "Target", performed.toFloat(),
                Color.parseColor("#90EE90")
            )
        )
        // To animate the pie chart







        pieChart.startAnimation()
    }




    private fun showError(msg: String){
        binding.tvHours.setTextColor(Color.RED)
        binding.tvHours.text = msg
    }



}
