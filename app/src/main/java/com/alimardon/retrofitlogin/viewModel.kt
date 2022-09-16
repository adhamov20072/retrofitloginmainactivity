package com.alimardon.retrofitlogin

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class viewModel(application: Application,val networkApi: NetworkApi) :
    AndroidViewModel(application) {
    val loginLiveData: MutableLiveData<NetworkResult<Login>> = MutableLiveData()
    @RequiresApi(Build.VERSION_CODES.M)
    fun doLogin(login: String, password: String) = viewModelScope.launch {
        loginSafeCall(login, password)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun loginSafeCall(login: String, password: String) {
        loginLiveData.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            val responselive = networkApi.login(login, password)

            if (responselive.isSuccessful) {
                loginLiveData.value = NetworkResult.Success(responselive.body())
            }
            else {
                loginLiveData.value = NetworkResult.Error(responselive.message())
            }

        } else {
            loginLiveData.value = NetworkResult.Error("no internet")
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun hasInternetConnection(): Boolean {
        val connectivityManager =
            getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}