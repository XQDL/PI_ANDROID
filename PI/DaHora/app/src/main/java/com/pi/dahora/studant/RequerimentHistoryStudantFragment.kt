package com.pi.dahora.studant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pi.dahora.R
import com.pi.dahora.databinding.FragmentRequerimentHistoryStudantBinding
import com.pi.dahora.login.LoginActivity

class RequerimentHistoryStudantFragment : Fragment() {

    private lateinit var binding: FragmentRequerimentHistoryStudantBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentRequerimentHistoryStudantBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Exmplo do dado de usuario logado vindo da variavel global
        binding.TextViewHistory.text = LoginActivity.userLogged.toString()

        //TODO: Funcionalidades aqui!

    }
}