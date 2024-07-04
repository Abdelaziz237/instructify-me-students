package com.instructify_me.students.auth.domain

import com.instructify_me.students.auth.domain.repository.AuthRepository

object AuthRepo {
    private lateinit var _repo: AuthRepository

    fun setRepo(repo: AuthRepository) {
        _repo = repo
    }

    fun getRepo(): AuthRepository {
        if (!::_repo.isInitialized) {
            throw IllegalStateException("AuthRepo is not initialized. Call setRepo() first.")
        }
        return _repo
    }
}