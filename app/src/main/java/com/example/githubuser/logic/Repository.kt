package com.example.currencyconversion.logic

import androidx.lifecycle.liveData
import com.example.currencyconversion.logic.network.ServiceNetWork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object Repository {

    fun getUsers(userName: String) = fire(Dispatchers.IO) {
        val userResponse = ServiceNetWork.getUsers(userName)
        val items = userResponse.items
        Result.success(items)
    }

    fun getUserRepo(userName: String) = fire(Dispatchers.IO) {
        val repoResponse = ServiceNetWork.getUserRepo(userName)
        Result.success(repoResponse)
    }

    fun getUserRepos(userName: String) = fire(Dispatchers.IO) {
        val unforkedRepoResponse = ServiceNetWork.getUserRepos(userName)
        Result.success(unforkedRepoResponse)
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}