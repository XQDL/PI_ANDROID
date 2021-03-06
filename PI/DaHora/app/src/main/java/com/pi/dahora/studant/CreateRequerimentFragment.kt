package com.pi.dahora.studant

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar
import com.pi.dahora.Models.EndpointRequirement
import com.pi.dahora.Models.Enum.Status
import com.pi.dahora.Models.Requirement
import com.pi.dahora.R
import com.pi.dahora.databinding.FragmentCreateRequirementBinding
import com.pi.dahora.utils.LoginUser
import com.pi.dahora.utils.NetworkUtils
import com.pi.dahora.utils.TimeUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class CreateRequerimentFragment : Fragment() {

    private lateinit var datePickerDialog : DatePickerDialog

    private lateinit var binding: FragmentCreateRequirementBinding

    private lateinit var button : Button

    private var errorMessage : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentCreateRequirementBinding.inflate(inflater)

        binding.ButtonSend.setOnClickListener { sendRequeriment() }

        initDatePicker()

        binding.ButtonDatePickerStart.setText(getTodaysDate())
        binding.ButtonDatePickerEnd.setText(getTodaysDate())

        binding.ButtonDatePickerStart.setOnClickListener{button = binding.ButtonDatePickerStart
            openDatePicker(binding.root) }
        binding.ButtonDatePickerEnd.setOnClickListener{button = binding.ButtonDatePickerEnd
            openDatePicker(binding.root)}

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Funcionalidades aqui!

        if(HasCompletedHours()){
            showError(errorMessage)
            binding.ButtonSend.isEnabled = false
        }

    }

    private fun sendRequeriment(){

        var isValid = validate()

        if(!isValid){
            showError(errorMessage)
            return
        }
        Snackbar.make(binding.root,"Carregando...", Snackbar.LENGTH_INDEFINITE).show()


        val retrofitClient = NetworkUtils.getRetrofitInstance("https://apidahora.herokuapp.com/api/")
        val endpoint = retrofitClient.create(EndpointRequirement::class.java)
        val requirement = createRequeriment()
        val callback = endpoint.createRequirement(requirement)

        callback.enqueue(object : Callback<Requirement> {
            override fun onFailure(call: Call<Requirement>, t: Throwable) {
                showError("Desculpe, ocorreu um erro interno no servidor!")
            }

            override fun onResponse(call: Call<Requirement>, response: Response<Requirement>) {
                errorMessage = when(response.raw().code()){
                    201 -> ""
                    500 -> "Desculpe, ocorreu um erro interno no servidor!"
                    else -> "Ops, ocorreu um erro inesperado!"
                }
                showError(errorMessage)


                if(errorMessage.isNullOrEmpty()){
                    Snackbar.make(binding.root,"Requerimento criado com sucesso!", Snackbar.LENGTH_LONG).show()
                    Thread.sleep(1000)
                    clearFields()
                }
            }
        })
    }

    //TODO: Tentar voltar para  a HOME_STUDANT_FRAGMENT
    private fun clearFields(){
        binding.TextInputComments.editText?.setText("")
        binding.TextInputWorkLoad.editText?.setText("")
        binding.EditTextTittleRequirement.editText?.setText("")
        binding.TextInputNameInstitution.editText?.setText("")
        binding.ButtonDatePickerStart.setText(getTodaysDate())
        binding.ButtonDatePickerEnd.setText(getTodaysDate())
    }

    private fun validate(): Boolean {
        var hasError = false

        hasError = hasError || dateIsInvalid()
        hasError = hasError || HasFieldsEmpty()

        return !hasError
    }

    private fun HasCompletedHours(): Boolean {


        if (LoginUser.userLogged.hasCompletedHours == true){
            errorMessage = "Voc?? j?? concluiu sua meta de horas complementares! N??o ?? mais necess??rio abrir requerimentos!"
            return true
        }
        return false
    }

    private fun dateIsInvalid(): Boolean {
        var startDateString = toDate(binding.ButtonDatePickerStart)
        var endDateString = toDate(binding.ButtonDatePickerEnd)

        var startDate = LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        var endDate = LocalDate.parse(endDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        val today = Calendar.getInstance().time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        if(today < startDate){
            errorMessage = "Data inicial n??o pode ser maior que a data atual"
            return true
        }
        if(today < endDate){
            errorMessage = "Data final n??o pode ser maior que a data atual"
            return true
        }
        if(startDate > endDate){
            errorMessage = "Data inicial n??o pode ser maior que a data final"
            return true
        }

        return false

    }

    private fun HasFieldsEmpty() : Boolean{
        var isError = false

        isError = isError || binding.EditTextTittleRequirement.editText?.text.toString().isNullOrEmpty()
        isError = isError || binding.TextInputWorkLoad.editText?.text.toString().isNullOrEmpty()
        isError = isError || binding.TextInputNameInstitution.editText?.text.toString().isNullOrEmpty()

        if(isError){
            errorMessage = "Preencha todos os  campos"
        }

        return isError
    }

    private fun showError(msg: String){
        binding.TextViewErrorLogin.visibility = View.VISIBLE
        binding.TextViewErrorLogin.setTextColor(Color.RED)
        binding.TextViewErrorLogin.text = msg

    }

    private fun createRequeriment() : Requirement {

        var requeriment = Requirement(
            id = 0,
            tittle = binding.EditTextTittleRequirement.editText?.text.toString(),
            startDate = toDate(binding.ButtonDatePickerStart),
            endDate = toDate(binding.ButtonDatePickerEnd),
            workLoad = binding.TextInputWorkLoad.editText?.text.toString().toDouble(),
            comments = binding.TextInputComments.editText?.text.toString() ?:"",
            attachmentAdress = "WIP",
            institutionName = binding.TextInputNameInstitution.editText?.text.toString(),
            createdTime = TimeUtils.getAtualHour(),
            student = LoginUser.userLogged.id.toLong(),
            approvedTime = null,
            reason = null,
            attachment = null,
            type = Status.CREATED.printableName
        )

        return requeriment
    }

    private fun getTodaysDate(): String? {
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        var month = cal[Calendar.MONTH]
        month = month + 1
        val day = cal[Calendar.DAY_OF_MONTH]
        return makeDateString(day, month, year)
    }

    private fun initDatePicker() {
        val dateSetListener =
            OnDateSetListener { datePicker, year, monthInial, day ->
                var month = monthInial
                month = month + 1
                val date = makeDateString(day, month, year)
                button.setText(date)
            }
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH]
        val day = cal[Calendar.DAY_OF_MONTH]
        val style = AlertDialog.THEME_HOLO_LIGHT
        datePickerDialog = DatePickerDialog(this.requireContext(), style, dateSetListener, year, month, day)

        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private fun makeDateString(day: Int, month: Int, year: Int): String? {
        return TimeUtils.getMonthString(month) + "/" + day + "/" + year
    }

    private fun openDatePicker(view: View?) {

        val date = button.text.split("/")

        datePickerDialog.updateDate(date[2].toInt(), TimeUtils.getMonthInt(date[0])-1,date[1].toInt())
        datePickerDialog.show()
    }

    private fun toDate(button : Button): String{
        val x = button.text.split("/")

        val stringDate = x[2] + "-" + TimeUtils.retorneString(TimeUtils.getMonthInt(x[0])) + "-" + TimeUtils.retorneString(x[1].toInt())

        return stringDate
    }
}