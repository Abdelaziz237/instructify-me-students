package com.instructify_me.students.profile.presentation.info

sealed class ProfileEvents {
    data class ChangeName(val name: String) : ProfileEvents()
    data class ChangeEmail(val email: String) : ProfileEvents()
    data class ChangeBio(val bio: String) : ProfileEvents()
    data class ChangeEdu(val edu: String) : ProfileEvents()
}