package com.pi.dahora

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.pi.dahora.Models.*
import com.pi.dahora.Models.Enum.Status
import com.pi.dahora.coordinator.RequerimentPendingFragment
import com.pi.dahora.databinding.FragmentViewRequisitionBinding
import com.pi.dahora.utils.LoginUser
import com.pi.dahora.utils.NetworkUtils
import com.pi.dahora.utils.TimeUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewRequisitionFragment(requirement: Requirement) : Fragment() {
    private lateinit var course: Course
    private lateinit var student: Student
    private lateinit var binding: FragmentViewRequisitionBinding
    private val requirement = requirement

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentViewRequisitionBinding.inflate(layoutInflater)

        getStudentById(requirement.student)

        return binding.root
    }

    private fun incrementView(requirement : Requirement){
        binding.apply {
            textName.text = student.name
            textDateInitial.text = dateFormater(requirement.createdTime)
            textDateFinal.text = if (requirement.approvedTime != null)  dateFormater(requirement.approvedTime!!) else "PENDENTE"
            textTittle.text = requirement.tittle
            textHours.text = formatHour(requirement.workLoad.toString())
            textDateRealization.text = "${dateFormater(requirement.startDate)} - ${dateFormater(requirement.endDate)}"
            textInstitution.text = requirement.institutionName
            textStatus.text = requirement.type
        }


        if(LoginUser.isCoordinator && requirement.type == Status.CREATED.printableName){
            binding.aprove.visibility = View.VISIBLE
            binding.reasonLayout.visibility = View.VISIBLE
            binding.reprove.visibility = View.VISIBLE
        } else{
            binding.aprove.visibility = View.GONE
            binding.reasonLayout.visibility = View.GONE
            binding.reprove.visibility = View.GONE
        }

        binding.aprove.setOnClickListener { aprove() }
        binding.reprove.setOnClickListener { reprove() }
    }

    private fun reprove() {
        Snackbar.make(binding.root,"Carregando...", Snackbar.LENGTH_LONG).show()
        var reason = binding.reasonLayout.editText?.text.toString()

        if(reason.isNullOrEmpty()){
            Snackbar.make(binding.root,"O motivo é obrigatório para excluir um requerimento!", Snackbar.LENGTH_LONG).show()
            return
        }

        requirement.approvedTime = TimeUtils.getAtualHour()
        requirement.type = Status.DENIED.printableName
        requirement.reason = binding.reasonLayout.editText?.text.toString()

        attRequirement()
    }




    private fun aprove() {
        Snackbar.make(binding.root,"Carregando...", Snackbar.LENGTH_LONG).show()


        requirement.approvedTime = TimeUtils.getAtualHour()
        requirement.type = Status.APPROVED.printableName
        requirement.reason = binding.reasonLayout.editText?.text.toString()

        attRequirement()

        student.additionalHoursPerformed += requirement.workLoad
        getCourseById(student.course)
    }

    private fun attStudent() {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://apidahora.herokuapp.com/api/")
        val endpoint = retrofitClient.create(EndpointStudent::class.java)

        val callback = endpoint.attStudent(student, student.id.toLong())

        callback.enqueue(object : Callback<Student> {
            override fun onFailure(call: Call<Student>, t: Throwable) {
                showError("Desculpe, ocorreu um erro interno no servidor!")
            }

            override fun onResponse(call: Call<Student>, response: Response<Student>) {
                val errorMensage = when(response.raw().code()){
                    204 -> ""
                    500 -> "Desculpe, ocorreu um erro interno no servidor!"
                    else -> "Ops, ocorreu um erro inesperado!"
                }
                val msg = if (requirement.type == Status.APPROVED.printableName) "aprovado" else "reprovado"

                if(!errorMensage.isNullOrEmpty()){
                    showError(errorMensage)
                }
                if(response.raw().code() == 204){
                    Snackbar.make(binding.root,"Requerimento "+ msg +" com sucesso!", Snackbar.LENGTH_LONG).show()
                    Thread.sleep(1000)

                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView_Coordinator, RequerimentPendingFragment())
                        .commit()
                }
            }
        })
    }


    private fun attRequirement() {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://apidahora.herokuapp.com/api/")
        val endpoint = retrofitClient.create(EndpointRequirement::class.java)

        val callback = endpoint.updateRequirement(requirement, requirement.id.toLong())

        callback.enqueue(object : Callback<Requirement> {
            override fun onFailure(call: Call<Requirement>, t: Throwable) {
                showError("Desculpe, ocorreu um erro interno no servidor!")
            }

            override fun onResponse(call: Call<Requirement>, response: Response<Requirement>) {

                val errorMensage = when(response.raw().code()){
                    204 -> ""
                    500 -> "Desculpe, ocorreu um erro interno no servidor!"
                    else -> "Ops, ocorreu um erro inesperado!"
                }

                if(!errorMensage.isNullOrEmpty()){
                    showError(errorMensage)
                }

            }
        })
    }


    private fun getStudentById(id:Long) {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://apidahora.herokuapp.com/api/")
        val endpoint = retrofitClient.create(EndpointStudent::class.java)

        val callback = endpoint.getStudentById(id)

        callback.enqueue(object : Callback<Student> {
            override fun onFailure(call: Call<Student>, t: Throwable) {
                showError("Desculpe, ocorreu um erro interno no servidor!")
            }

            override fun onResponse(call: Call<Student>, response: Response<Student>) {

                val errorMensage = when(response.raw().code()){
                    200 -> ""
                    500 -> "Desculpe, ocorreu um erro interno no servidor!"
                    else -> "Ops, ocorreu um erro inesperado!"
                }

                if(!errorMensage.isNullOrEmpty()){
                    showError(errorMensage)
                }

                val studentTemp = response.body()

                if (studentTemp != null){
                    student = studentTemp
                    incrementView(requirement)
                }
            }
        })
    }


    private fun getCourseById(courseId: Long) {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://apidahora.herokuapp.com/api/")
        val endpoint = retrofitClient.create(EndpointCourse::class.java)



        val callback = endpoint.getCourseById(courseId)

        callback.enqueue(object : Callback<Course> {
            override fun onFailure(call: Call<Course>, t: Throwable) {
            }

            override fun onResponse(call: Call<Course>, response: Response<Course>) {
                val erroMensage = when(response.raw().code()){
                    404 -> "E-mail ou senha inválidos"
                    500 -> "Desculpe, ocorreu um erro interno no servidor!"
                    else -> "Ops, ocorreu um erro inesperado!"
                }
                if(!erroMensage.isNullOrEmpty()){
                    showError(erroMensage)
                }


                try{
                    course = response.body()!!
                    if(student.additionalHoursPerformed >= course.additionalHoursTarget){
                        student.hasCompletedHours = true
                    }

                    attStudent()
                } catch(e : Exception){
                    showError("Erro interno!")
                }
            }
        })
    }



    private fun showError(msg: String){
        Snackbar.make(binding.root,msg, Snackbar.LENGTH_LONG).show()
    }

    private fun dateFormater(date : String): String{
        var splitedDate = date.split("T")[0].split("-")

        var day = splitedDate[2].toInt()
        var month = splitedDate[1].toInt()
        var year = splitedDate[0].toInt()

        return makeDateString(day, month, year)
    }

    private fun makeDateString(day: Int, month: Int, year: Int): String {
        return  formatDay(day) + "/" + TimeUtils.getMonthString(month) + "/" + year
    }

    private fun formatDay(day: Int) : String{
        if (day < 10){
            return "0$day"
        }
        return day.toString()
    }



    private fun formatHour(hour: String): String {
        var splited = hour.split(".")
        var formattedHour : String

        if(splited[1] == "0"){
            formattedHour = splited[0]
        } else{
            formattedHour = hour
        }

        return formattedHour+" Horas"
    }

}