package com.pi.dahora

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.pi.dahora.databinding.ActivityHomeStudantBinding

class HomeStudant : AppCompatActivity() {

    //private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeStudantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeStudantBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setSupportActionBar(binding.toolbar)

        navigationViews()
    }

    private fun navigationViews() {
        binding.menuStudant.apply {
            setOnItemSelectedListener{item ->
                when(item.itemId) {
                    R.id.menu_historic -> historic()
                    R.id.menu_search -> teste()
                    R.id.menu_home -> requeriment()
                    else -> Toast.makeText(this@HomeStudant, "Fururamente...", Toast.LENGTH_SHORT).show()
                }
                true
            }

        }
    }

    private fun historic() {
        setContentView(R.layout.activity_requirement_history_studant)
    }

    private fun requeriment(){
        finish()
        Intent(this, CreateRequirementActivity::class.java).apply {
            startActivity(this)
        }

    }

    private fun teste(){
        Intent(this, CreateRequirementActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(this)
        }
    }
}

