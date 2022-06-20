package com.pi.dahora.studant

import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.pi.dahora.ItemClickListener
import com.pi.dahora.Models.*
import com.pi.dahora.R
import com.pi.dahora.RequirementAdpter
import com.pi.dahora.ViewRequisitionFragment
import com.pi.dahora.coordinator.HomeCoordinatorActivity
import com.pi.dahora.databinding.FragmentCoordinatorProfileBinding
import com.pi.dahora.databinding.FragmentRequerimentHistoryStudantBinding
import com.pi.dahora.databinding.FragmentViewRequisitionBinding
import com.pi.dahora.login.LoginActivity
import com.pi.dahora.utils.LoginUser
import com.pi.dahora.utils.NetworkUtils
import com.pi.dahora.utils.Requirements
import com.pi.dahora.utils.TimeUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequerimentHistoryStudantFragment : Fragment() {

    private lateinit var binding: FragmentRequerimentHistoryStudantBinding
    private lateinit var requirements : List<Requirement>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRequerimentHistoryStudantBinding.inflate(inflater)

        getData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun recycleView(){
        val onClickListener = ItemClickListener{requirement ->
            bottomSheet(requirement)

//            val fragment = ViewRequisitionFragment(requirement)
//            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerView_Student, fragment).commit()
        }

        val recyclerView = binding.recycleViewS
        val adpter = RequirementAdpter(requirements, onClickListener)
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false )

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adpter
    }

    private fun getData() {
        Snackbar.make(binding.root,"Carregando...", Snackbar.LENGTH_INDEFINITE).show()

        val retrofitClient =
            NetworkUtils.getRetrofitInstance("https://apidahora.herokuapp.com/api/")
        val endpoint = retrofitClient.create(EndpointRequirement::class.java)
        var id = LoginUser.userLogged.id.toLong()

        val callback = endpoint.getRequirementsByStudent(id)

        callback.enqueue(object : Callback<List<Requirement>> {
            override fun onFailure(call: Call<List<Requirement>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Requirement>>, response: Response<List<Requirement>>) {
                val requirementsTemp = response.body()

                if (requirementsTemp != null) {
                    requirements = requirementsTemp
                    recycleView()
                    Snackbar.make(binding.root,"", 1).show()

                }
            }
        })
    }

    private fun bottomSheet(requirement : Requirement){

        val bottomSheetBinding : FragmentViewRequisitionBinding =  FragmentViewRequisitionBinding.inflate(layoutInflater)

        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetBinding.root)


        bottomSheetBinding.fixedTextName.visibility = View.GONE
        bottomSheetBinding.textName.visibility = View.GONE
        bottomSheetBinding.textDateInitial.text = TimeUtils.dateFormater(requirement.createdTime)
        bottomSheetBinding.textDateFinal.text = if (requirement.approvedTime != null)  TimeUtils.dateFormater(requirement.approvedTime!!) else "PENDENTE"
        bottomSheetBinding.textTittle.text = requirement.tittle
        bottomSheetBinding.textHours.text = requirement.workLoad.toString()
        bottomSheetBinding.textDateRealization.text = "${TimeUtils.dateFormater(requirement.startDate)} - ${TimeUtils.dateFormater(requirement.endDate)}"
        bottomSheetBinding.textInstitution.text = requirement.institutionName
        bottomSheetBinding.textStatus.text = TimeUtils.toPortugues(requirement.type)

        bottomSheetDialog.show()
    }

}