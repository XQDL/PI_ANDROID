package com.pi.dahora.studant

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.pi.dahora.R
import com.pi.dahora.databinding.FragmentCreateRequerimentBinding
import org.w3c.dom.Text

class CreateRequerimentFragment : Fragment() {

    private lateinit var binding: FragmentCreateRequerimentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentCreateRequerimentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Funcionalidades aqui!

    }

}