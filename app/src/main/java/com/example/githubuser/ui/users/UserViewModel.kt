package com.example.githubuser.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.currencyconversion.logic.Repository
import com.example.githubuser.logic.model.Items

class UserViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val items = ArrayList<Items>()

    val usersLiveData = Transformations.switchMap(searchLiveData) { userName ->
        Repository.getUsers(userName)
    }

    fun getUsers(userName: String) {
        searchLiveData.value = userName
    }
}