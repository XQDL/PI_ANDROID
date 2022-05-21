package com.pi.dahora.studant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pi.dahora.R
import com.pi.dahora.databinding.ActivityHomeStudantBinding


class HomeStudantActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeStudantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeStudantBinding.inflate(layoutInflater)
        setContentView(binding.root)


        (binding.bottomNavigationStudent).selectedItemId = R.id.homeStudantFragment

        goHome()

        navigationViews()
    }



    private fun navigationViews() {
        binding.bottomNavigationStudent.apply {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.createRequerimentFragment -> goCreate()
                    R.id.homeStudantFragment -> goHome()
                    R.id.requerimentHistoryStudantFragment -> goHistory()
                }
                true
            }
        }
    }

    private fun goHistory() {
        binding.toolbarStudent.title = "Histórico"

        val fragment = RequerimentHistoryStudantFragment()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView_Student, fragment, "")
        fragmentTransaction.commit()
    }

    fun goHome() {
        binding.toolbarStudent.title = "DaHora"

        val fragment = HomeStudantFragment()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView_Student, fragment, "")
        fragmentTransaction.commit()
    }

    private fun goCreate() {
        binding.toolbarStudent.title = "Nova Requisição"

        val fragment = CreateRequerimentFragment()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView_Student, fragment, "")
        fragmentTransaction.commit()
    }
}