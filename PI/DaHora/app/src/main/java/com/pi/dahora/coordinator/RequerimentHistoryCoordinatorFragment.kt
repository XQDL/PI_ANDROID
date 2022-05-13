package com.pi.dahora.coordinator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pi.dahora.R
import com.pi.dahora.databinding.FragmentRequerimentHistoryCoordinatorBinding
import com.pi.dahora.databinding.FragmentViewRequisitionBinding

class RequerimentHistoryCoordinatorFragment : Fragment() {
    private lateinit var binding: FragmentRequerimentHistoryCoordinatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequerimentHistoryCoordinatorBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

}