package com.alimardon.retrofitlogin

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alimardon.retrofitlogin.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var preferences: SharedPreferences
    lateinit var binding:ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}