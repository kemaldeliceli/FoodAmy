package com.lesson.foodamy.repository

import com.lesson.foodamy.model.ResponseLogout
import com.lesson.foodamy.model.ResponseUser
import com.lesson.foodamy.model.dataclass.AuthData
import com.lesson.foodamy.model.dataclass.RegisterData
import com.lesson.foodamy.services.AuthService

class AuthAPIRepository(private val authService: AuthService) : BaseRepository() {

    suspend fun requestLogin(authData: AuthData): ResponseUser? =
        sendRequest{
            authService.getAuth(authData.email, authData.password)
        }

    suspend fun requestRegister(registerData: RegisterData): ResponseUser? =
        sendRequest{authService.register(registerData.email,
            registerData.password,
            registerData.username)}

    suspend fun requestLogout(): ResponseLogout?=
        sendRequest { authService.logOut() }
}
