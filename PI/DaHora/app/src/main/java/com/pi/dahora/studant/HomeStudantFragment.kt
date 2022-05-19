package com.pi.dahora.studant

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pi.dahora.ViewRequisitionFragment
import com.pi.dahora.databinding.FragmentHomeStudantBinding
import com.pi.dahora.utils.Requirements

class HomeStudantFragment : Fragment() {
    private lateinit var binding: FragmentHomeStudantBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeStudantBinding.inflate(inflater)
        binding.TextViewTesteStudent.setOnClickListener {
            x()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Funcionalidades aqui!

    }

    private fun x() {
        val requirements = Requirements().loadRequirements()
        val requirement = requirements[1]
        //Intent(binding.root, ViewRequisitionFragment(requirement)::class.java).apply {

    }
}
