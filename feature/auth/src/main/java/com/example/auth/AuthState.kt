package com.example.auth

sealed class AuthState()

object AuthProgress : AuthState()
class AuthError(val errorCode: Int): AuthState()
object AuthSuccess: AuthState()
