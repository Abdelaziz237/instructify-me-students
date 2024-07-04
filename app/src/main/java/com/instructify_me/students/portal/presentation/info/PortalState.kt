package com.instructify_me.students.portal.presentation.info

data class PortalState(
    val tags: List<String>,
    val tutors: List<TutorState>
)

data class TutorState(
    val name: String,
    val jobTitle: String,
    val jobSite: String,
    val isAvailable: Boolean,
    val tags: List<String>,
    val sessionFees: String,
    val similarity: Double,
    val availability: Boolean = false
)

