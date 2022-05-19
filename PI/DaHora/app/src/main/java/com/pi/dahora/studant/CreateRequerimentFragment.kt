package com.pi.dahora.studant

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.pi.dahora.Models.*
import com.pi.dahora.databinding.FragmentCreateRequirementBinding
import com.pi.dahora.utils.LoginUser
import com.pi.dahora.utils.NetworkUtils
import com.pi.dahora.utils.TimeUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CreateRequerimentFragment : Fragment() {

    private lateinit var datePickerDialog : DatePickerDialog

    private lateinit var binding: FragmentCreateRequirementBinding

    private lateinit var button : Button

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

    }

    private fun sendRequeriment(){

        val retrofitClient = NetworkUtils.getRetrofitInstance("https://apidahora.herokuapp.com/api/")
        val endpoint = retrofitClient.create(EndpointRequirement::class.java)
        val requirement = createRequeriment()
        val callback = endpoint.createRequirement(requirement)

        callback.enqueue(object : Callback<Requirement> {
            override fun onFailure(call: Call<Requirement>, t: Throwable) {

            }

            override fun onResponse(call: Call<Requirement>, response: Response<Requirement>) {
                var x = response.body()
            }
        })
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
            type = "created",
            status = null
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

    companion object{

    }

}