package com.pi.dahora.studant

import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
        val dado = intent.getSerializableExtra("aluno")
        setContentView(binding.root)

        navigationViews()
//
        val homeFragment = CreateRequerimentFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, homeFragment, "")
        fragmentTransaction.commit()


        //findViewById<TextView>(R.id.text_xqdl).text = dado.toString()

//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.menu_studant)
//
//
//
//        try {
//            navigationViews()
//
//            val homeFragment = CreateRequerimentFragment()
//            val fragmentTransaction = supportFragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.content, homeFragment, "")
//            fragmentTransaction.commit()
//
//
//            val navController = findNavController(R.id.fragmentContainerView)
//            bottomNavigationView.setupWithNavController(navController)
//        }
//        catch (e: Throwable){
//            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
//            Log.e("ERRO", e.toString())
//        }

//        val navigationView = findViewById<BottomNavigationView>(R.id.menu_studant)

        //binding.menuStudant.setupWithNavController(navController)
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
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment, "")
        fragmentTransaction.commit()

    }

    private fun goHome() {
        val fragment = HomeStudantFragment()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment, "")
        fragmentTransaction.commit()
    }

    private fun goCreate() {
        val fragment = CreateRequerimentFragment()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment, "")
        fragmentTransaction.commit()
    }


}