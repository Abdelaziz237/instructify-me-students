package com.instructify_me.students.auth.domain.usecases

data class AuthUseCases(
    val signIn: SignInUseCase,
    val signUp: SignUpUseCase
)
