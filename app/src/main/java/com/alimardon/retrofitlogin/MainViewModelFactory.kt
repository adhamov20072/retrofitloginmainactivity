package com.alimardon.retrofitlogin

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alimardon.retrofitlogin.NetworkApi as NetworkApi1

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val application: Application, private val networkApi: NetworkApi1): ViewModelProvider.Factory {
    override fun <T:ViewModel>create(modelClass:Class<T>):T {
        if (modelClass.isAssignableFrom(viewModel::class.java)) {
            return viewModel(application,networkApi) as T
        }
    throw  IllegalArgumentException("Error")
    }
}