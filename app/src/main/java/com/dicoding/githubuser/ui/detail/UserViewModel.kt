package com.dicoding.githubuser.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.dicoding.githubuser.api.RetrofitClient
import com.dicoding.githubuser.data.model.User
import com.dicoding.githubuser.data.model.UserResponse
import com.dicoding.githubuser.data.setting.SettingPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(private val preferences: SettingPreferences) : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<User>>()

    fun getTheme() = preferences.getThemeSetting().asLiveData()

    fun setSearchUsers(query: String) {
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }
            })
    }


    fun getSearchUsers(): LiveData<ArrayList<User>> {
        return listUsers
    }

    class Factory(private val preferences: SettingPreferences):
            ViewModelProvider.NewInstanceFactory(){

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            UserViewModel(preferences) as T
            }
}