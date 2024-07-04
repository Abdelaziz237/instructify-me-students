package com.instructify_me.students.auth.domain.usecases

import com.instructify_me.students.auth.domain.repository.AuthRepository

data class AuthUseCases(
    val signIn: SignInUseCase,
    val signUp: SignUpUseCase,
) {
    constructor(repo: AuthRepository) : this(
        signIn = SignInUseCase(repo),
        signUp = SignUpUseCase(repo),
    )
}
