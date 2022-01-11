package com.example.currencyconversion.logic.network

import com.example.githubuser.logic.network.RepoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object ServiceNetWork {

    private val userService = ServiceCreator.create<UserService>()
    private val repoService = ServiceCreator.create<RepoService>()

    suspend fun getUsers(userName: String) = userService.getUsers(userName).await()

    suspend fun getUserRepo(userName: String) = repoService.getRepos(userName).await()

    suspend fun getUserRepos(userName: String) = repoService.getUserRepos(userName).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

}