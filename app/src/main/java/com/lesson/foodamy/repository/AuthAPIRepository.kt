package com.lesson.foodamy.repository

import com.lesson.foodamy.model.ResponseLogout
import com.lesson.foodamy.model.ResponseUser
import com.lesson.foodamy.model.dataclass.AuthData
import com.lesson.foodamy.model.dataclass.RegisterData
import com.lesson.foodamy.preferences.IPrefDefaultManager
import com.lesson.foodamy.services.AuthService

class AuthAPIRepository(
    private val authService: AuthService,
    private val prefManager: IPrefDefaultManager
) : BaseRepository() {

    suspend fun requestLogin(authData: AuthData): ResponseUser? =
        sendRequest {
            val response = authService.getAuth(authData.email, authData.password)
            response.token?.let {
                prefManager.setToken(it)
            }
            response
        }

    suspend fun requestRegister(registerData: RegisterData): ResponseUser? =
        sendRequest {
            val response = authService.register(
                registerData.email,
                registerData.password,
                registerData.username
            )
            response.token?.let {
                prefManager.setToken(it)
            }
            response
        }

    suspend fun requestLogout(): ResponseLogout =
        sendRequest {
            prefManager.setToken("")
            prefManager.saveLogin(false)
            prefManager.setUserInfo(null)
            authService.logOut()
        }
}
