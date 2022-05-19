package com.pi.dahora.studant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.pi.dahora.ItemClickListener
import com.pi.dahora.R
import com.pi.dahora.RequirementAdpter
import com.pi.dahora.databinding.FragmentRequerimentHistoryStudantBinding
import com.pi.dahora.login.LoginActivity
import com.pi.dahora.utils.LoginUser
import com.pi.dahora.utils.Requirements

class RequerimentHistoryStudantFragment : Fragment() {

    private lateinit var binding: FragmentRequerimentHistoryStudantBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRequerimentHistoryStudantBinding.inflate(inflater)

        recycleView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun recycleView(){
        val onClickListener = ItemClickListener{requirement ->
            Toast.makeText(this.context, "'${requirement.tittle}' clicked", Toast.LENGTH_SHORT).show()
        }

        val recyclerView = binding.recycleViewS
        val adpter = RequirementAdpter(Requirements().loadRequirements(), onClickListener)
        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false )

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adpter
    }
}