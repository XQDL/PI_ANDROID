package com.pi.dahora.coordinator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.pi.dahora.R
import com.pi.dahora.databinding.ActivityHomeCoordinatorBinding
import com.pi.dahora.studant.CreateRequerimentFragment
import com.pi.dahora.studant.HomeStudantFragment
import com.pi.dahora.studant.RequerimentHistoryStudantFragment

class HomeCoordinatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeCoordinatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeCoordinatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requerimentHistoryFragment = RequerimentHistoryCoordinatorFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerViewS, requerimentHistoryFragment, "")
        fragmentTransaction.commit()

        navigationViews()
    }

    private fun navigationViews() {
        binding.menuCoordinator.apply {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.requerimentPendingFragment -> goCreate()
                    R.id.studentsListFragment -> goHome()
                    R.id.requerimentHistoryCoordinatorFragment -> goHistory()
                }
                true
            }
        }
    }

    private fun goHistory() {
        val fragment = RequerimentHistoryCoordinatorFragment()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerViewS, fragment, "")
        fragmentTransaction.commit()
    }

    private fun goHome() {
        val fragment = StudentsListFragment()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerViewS, fragment, "")
        fragmentTransaction.commit()
    }

    private fun goCreate() {
        val fragment = RequerimentPendingFragment()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerViewS, fragment, "")
        fragmentTransaction.commit()
    }
}