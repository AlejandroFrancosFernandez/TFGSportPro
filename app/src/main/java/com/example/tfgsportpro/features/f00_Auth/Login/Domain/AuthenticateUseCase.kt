package com.example.tfgsportpro.features.f00_Auth.Login.Domain

import com.example.tfgsportpro.features.f00_Auth.Login.UseCase.LoginManager

//Vereficar que le llega un email y una password; además conecta la capa de interfazz(UI), con la de UseCase
class AuthenticateUseCase(private val loginManager: LoginManager) {

    fun execute(email: String, password: String, onComplete: (Boolean) -> Unit) {
        loginManager.loginWithEmailAndPassword(email, password, onComplete)
    }
}
