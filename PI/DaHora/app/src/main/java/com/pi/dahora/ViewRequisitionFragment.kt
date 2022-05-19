package com.pi.dahora

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pi.dahora.Models.Requirement
import com.pi.dahora.databinding.FragmentViewRequisitionBinding

class ViewRequisitionFragment(requirement: Requirement) : Fragment() {
    private lateinit var binding: FragmentViewRequisitionBinding
    private val requirement = requirement

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentViewRequisitionBinding.inflate(layoutInflater)

        incrementView(requirement)

        return binding.root
    }

    private fun incrementView(requirement : Requirement){
        binding.studentName.text = requirement.student.toString()
        binding.statusRequirement.text = requirement.type
        binding.dateStarRequirement.text = requirement.createdTime
        binding.dateCompletionRequirement.text = requirement.approvedTime
        binding.courseName.text = "Anasi"
        binding.tittleRequirement.text = requirement.tittle
        binding.workload.text = requirement.workLoad.toString()
        binding.dateStartActivity.text = requirement.startDate
        binding.dateEndActivity.text = requirement.endDate
        binding.intitutionName.text = requirement.institutionName

    }
}