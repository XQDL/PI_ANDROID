package com.pi.dahora.studant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pi.dahora.R
import com.pi.dahora.databinding.ActivityHomeStudantBinding


class HomeStudantActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeStudantBinding
    private lateinit var navigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeStudantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dado = intent.getSerializableExtra("aluno")

        (binding.menuStudant).selectedItemId = R.id.homeStudantFragment


        val homeFragment = HomeStudantFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerViewS, homeFragment, "")
        fragmentTransaction.commit()

        navigationViews()
    }

    private fun navigationViews() {
        binding.menuStudant.apply {
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
        val fragment = RequerimentHistoryStudantFragment()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerViewS, fragment, "")
        fragmentTransaction.commit()
    }

    private fun goHome() {
        val fragment = HomeStudantFragment()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerViewS, fragment, "")
        fragmentTransaction.commit()
    }

    private fun goCreate() {
        val fragment = CreateRequerimentFragment()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerViewS, fragment, "")
        fragmentTransaction.commit()
    }
}