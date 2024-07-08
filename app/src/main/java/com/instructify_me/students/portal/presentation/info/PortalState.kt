package com.instructify_me.students.portal.presentation.info

data class PortalState(
    val tags: List<String> = emptyList(),
    val tutors: List<TutorState> = emptyList()
)

data class TutorState(
    val name: String,
    val jobTitle: String,
    val jobSite: String,
    val bio: String,
    val isAvailable: Boolean,
    val tags: List<String>,
    val sessionFees: String,
    val similarity: Double,
    val availability: Boolean = false
)

