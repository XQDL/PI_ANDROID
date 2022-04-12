package com.pi.dahora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pi.dahora.databinding.ActivityCreateRequirementBinding

class CreateRequirementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateRequirementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateRequirementBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}