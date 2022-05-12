package com.pi.dahora

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pi.dahora.databinding.FragmentViewRequisitionBinding

class ViewRequisitionFragment : Fragment() {
    private lateinit var binding: FragmentViewRequisitionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentViewRequisitionBinding.inflate(layoutInflater)

        return binding.root
    }

}