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
import com.pi.dahora.databinding.FragmentCreateRequerimentBinding
import com.pi.dahora.utils.LoginUser
import com.pi.dahora.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CreateRequerimentFragment : Fragment() {

    private lateinit var datePickerDialog : DatePickerDialog

    private lateinit var binding: FragmentCreateRequerimentBinding

    private lateinit var button : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentCreateRequerimentBinding.inflate(inflater)

        binding.btnEnviar.setOnClickListener { sendRequeriment() }

        initDatePicker()

        binding.datePickerButtonStart.setText(getTodaysDate())
        binding.datePickerButtonEnd.setText(getTodaysDate())

        binding.datePickerButtonStart.setOnClickListener{button = binding.datePickerButtonStart
            openDatePicker(binding.root) }
        binding.datePickerButtonEnd.setOnClickListener{button = binding.datePickerButtonEnd
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
            tittle = binding.tdTittleRequeriment.editText?.text.toString(),
            startDate = toDate(binding.datePickerButtonStart),
            endDate = toDate(binding.datePickerButtonEnd),
            workLoad = binding.tdWorkLoad.editText?.text.toString().toDouble(),
            comments = binding.tdObserver.editText?.text.toString() ?:"",
            attachmentAdress = "WIP",
            institutionName = binding.tdNameInsti.editText?.text.toString(),
            createdTime = getAtualHour(),
            student = LoginUser.userLogged.id.toLong(),
            approvedTime = null,
            reason = null,
            attachment = null,
            type = null
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
        return getMonthFormat(month) + "/" + day + "/" + year
    }

    private fun getMonthFormat(month: Int): String {
        if (month == 1) return "JAN"
        if (month == 2) return "FEB"
        if (month == 3) return "MAR"
        if (month == 4) return "APR"
        if (month == 5) return "MAY"
        if (month == 6) return "JUN"
        if (month == 7) return "JUL"
        if (month == 8) return "AUG"
        if (month == 9) return "SEP"
        if (month == 10) return "OCT"
        if (month == 11) return "NOV"
        return if (month == 12) "DEC" else "JAN"

        //default should never happen
    }

    private fun getMonthFormatInverse(month: String): Int{
            if (month =="JAN" ) return 1
            if (month =="FEB" ) return 2
            if (month =="MAR" ) return 3
            if (month =="APR" ) return 4
            if (month =="MAY" ) return 5
            if (month =="JUN" ) return 6
            if (month =="JUL" ) return 7
            if (month =="AUG" ) return 8
            if (month =="SEP" ) return 9
            if (month == "OCT") return 10
            if (month == "NOV") return 11
            return if (month == "DEC") 12 else 1
    }

    private fun openDatePicker(view: View?) {

        val date = button.text.split("/")

        datePickerDialog.updateDate(date[2].toInt(), getMonthFormatInverse(date[0])-1,date[1].toInt())
        datePickerDialog.show()
    }

    private fun toDate(button : Button): String{
        val x = button.text.split("/")

        val stringDate = x[2] + "-" + retorneString(getMonthFormatInverse(x[0])) + "-" + retorneString(x[1].toInt())

        return stringDate
    }

    fun getAtualHour(): String{
        //val date = Calendar.getInstance().time

        val date = Calendar.getInstance()
        val day = date[Calendar.DATE]
        val month = date[Calendar.MONTH] + 1
        val year = date[Calendar.YEAR]
        val hour = date[Calendar.HOUR]
        val min = date[Calendar.MINUTE]
        val seg = date[Calendar.SECOND]

        val stringDate : String = year.toString() + "-" + retorneString(month) + "-" +
                retorneString(day) + "T" + retorneString(hour) + ":" + retorneString(min) + ":" + retorneString(seg)

        return stringDate
    }

    private fun retorneString(n : Int) : String{
        var stringNumber: String = n.toString()

        if (n<10){
            stringNumber = "0" + n.toString()
        }
        return stringNumber
    }
}