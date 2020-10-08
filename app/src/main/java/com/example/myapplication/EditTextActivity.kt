package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityEdittextBinding

class EditTextActivity:AppCompatActivity() {

    lateinit var binding: ActivityEdittextBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEdittextBinding.inflate(layoutInflater)
        binding.model = DupaViewData()
        binding.lifecycleOwner = this
        setContentView(binding.root)
    }
}
