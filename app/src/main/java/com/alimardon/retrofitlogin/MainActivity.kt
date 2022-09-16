package com.alimardon.retrofitlogin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.alimardon.retrofitlogin.databinding.ActivityMainBinding
import okhttp3.OkHttpClient

class MainActivity() : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var preferences: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences = getSharedPreferences("baza", MODE_PRIVATE)
        val interceptor = MyInterceptor(preferences)
        val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = RetrofitHelper.getRetrofit(okHttpClient).create(NetworkApi::class.java)
        val factory = MainViewModelFactory(application, retrofit)
        val viewModel = ViewModelProvider(this, factory)[viewModel::class.java]
        viewModel.loginLiveData.observe(this) {
            when (it) {

                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {
                }
                is NetworkResult.Success -> {
                    val ed = preferences.edit()
                    ed.putString("token", it.data!!.data[0].token)
                    ed.apply()
                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                    startActivity(intent)
                }
                else -> {}
            }
        }

        binding.button.setOnClickListener {
            val login = binding.editlogin.text.toString()
            val password = binding.editpassword.text.toString()
            viewModel.doLogin(login, password)
        }
    }

}