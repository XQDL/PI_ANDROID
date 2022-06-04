package com.pi.dahora.coordinator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.pi.dahora.R
import com.pi.dahora.databinding.ActivityHomeCoordinatorBinding

class HomeCoordinatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeCoordinatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeCoordinatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (binding.bottomNavigationCoordinator).selectedItemId = R.id.studentsListFragment

        goHome()

        navigationViews()
    }

    private fun navigationViews() {
        binding.bottomNavigationCoordinator.apply {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.requerimentPendingFragment -> goPending()
                    R.id.studentsListFragment -> goHome()
                    R.id.requerimentHistoryCoordinatorFragment -> goHistory()
                }
                true
            }
        }
    }

    private fun goHistory() {
        binding.toolbarCoordinator.title = "Histórico de requerimentos"
        val fragment = RequerimentHistoryCoordinatorFragment()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView_Coordinator, fragment, "")
        fragmentTransaction.commit()
    }

    private fun goHome() {
        binding.toolbarCoordinator.title = "Lista de alunos"
        val fragment = StudentsListFragment()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView_Coordinator, fragment, "")
        fragmentTransaction.commit()
    }

    private fun goPending() {
        binding.toolbarCoordinator.title = "Requerimentos pendentes"
        val fragment = RequerimentPendingFragment()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView_Coordinator, fragment, "")
        fragmentTransaction.commit()
    }
}