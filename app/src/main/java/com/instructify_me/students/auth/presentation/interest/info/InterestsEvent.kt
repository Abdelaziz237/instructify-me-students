package com.instructify_me.students.auth.presentation.interest.info

sealed class InterestsEvent {
    data class PostInterests(val interests: List<String>) : InterestsEvent()
    object GetInterests : InterestsEvent()
}