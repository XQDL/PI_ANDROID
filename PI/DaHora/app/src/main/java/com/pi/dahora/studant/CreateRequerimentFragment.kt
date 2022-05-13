package com.pi.dahora.studant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pi.dahora.Models.*
import com.pi.dahora.databinding.FragmentCreateRequerimentBinding
import com.pi.dahora.utils.LoginUser
import com.pi.dahora.utils.NetworkUtils
import com.pi.dahora.utils.TimeUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class CreateRequerimentFragment : Fragment() {

    private lateinit var binding: FragmentCreateRequerimentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentCreateRequerimentBinding.inflate(inflater)

        binding.btnEnviar.setOnClickListener { sendRequeriment() }

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
        val formato = SimpleDateFormat("yyyy-mm-dd")



        var requeriment = Requirement(
            id = 0,
            tittle = binding.tdTittleRequeriment.editText?.text.toString(),
            startDate = formato.parse(binding.tipDateStart.editText?.text.toString()),
            endDate = formato.parse(binding.tipDateEnd.editText?.text.toString()),
            workLoad = binding.tdWorkLoad.editText?.text.toString().toDouble(),
            comments = binding.tdObserver.editText?.text.toString() ?:"",
            attachmentAdress = "WIP",
            institutionName = binding.tdNameInsti.editText?.text.toString(),
            createdTime = TimeUtils.getAtualHour(),
            student = LoginUser.userLogged.id.toLong(),
            approvedTime = null,
            reason = null
        )

        return requeriment
    }
}