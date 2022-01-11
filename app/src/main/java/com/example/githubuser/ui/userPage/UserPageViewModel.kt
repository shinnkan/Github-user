package com.example.githubuser.ui.userPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.currencyconversion.logic.Repository
import com.example.githubuser.logic.model.UnforkedRepoResponse

class UserPageViewModel : ViewModel() {

    private val descLiveData = MutableLiveData<String>()
    private val repoLiveData = MutableLiveData<String>()

    val unforkedRepos = ArrayList<UnforkedRepoResponse>()

    val userPageLiveData = Transformations.switchMap(descLiveData) { userName ->
        Repository.getUserRepo(userName)
    }

    val userReposLiveData = Transformations.switchMap(repoLiveData) { userName ->
        Repository.getUserRepos(userName)
    }

    fun getUserRepo(userName: String) {
        descLiveData.value = userName
    }

    fun getUserRepos(userName: String) {
        repoLiveData.value = userName
    }
}