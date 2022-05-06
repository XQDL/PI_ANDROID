package com.pi.dahora.studant

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.pi.dahora.CreateRequirementActivity
import com.pi.dahora.R
import com.pi.dahora.databinding.FragmentHomeStudantBinding
import com.pi.dahora.databinding.ActivityHomeStudantBinding

class HomeStudantFragment : Fragment() {
    private lateinit var binding: FragmentHomeStudantBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeStudantBinding.inflate(inflater, container, false)

        return binding.root
    }

//    private fun navigationViews() {
//        binding2.menuStudant.apply {
//            setOnItemSelectedListener{item ->
//                when(item.itemId) {
//                    R.id.menu_historic -> historic()
//                    R.id.menu_search -> teste()
//                    R.id.menu_home -> requeriment()
//                    else -> Toast.makeText(context, "Fururamente...", Toast.LENGTH_SHORT).show()
//                }
//                true
//            }
//
//        }
//   }
//
//    private fun historic() {
//        Navigation.findNavController()
//    }
//
//    private fun requeriment(){
//        finish()
//        Intent(this, CreateRequirementActivity::class.java).apply {
//            startActivity(this)
//        }
//
//    }
//
//    private fun teste(){
//        Intent(this, CreateRequirementActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NO_HISTORY
//            startActivity(this)
//        }
//    }
}