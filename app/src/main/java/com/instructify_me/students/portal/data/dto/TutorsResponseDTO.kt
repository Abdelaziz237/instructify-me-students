package com.instructify_me.students.portal.data.dto

data class TutorsResponseDTO(
    val instructor: Instructor,
    val adjustedSimilarity: Double
)

data class Instructor(
    val rate: Map<String, Int>,
    val _id: String,
    val name: String,
    val email: String,
    val password: String,
    val status: String,
    val skills: List<Skill>,
    val experience: List<Experience>,
    val availability: List<Availability>,
    val __v: Int,
    val price_hour: Int,
    val bio: String
)

data class Availability(
    val dayOfWeek: String,
    val startTime: String,
    val endTime: String,
    val _id: String = ""
)

data class Skill(
    val _id: String = "",
    val tag: String
)

data class Experience(
    val position: String,
    val site: String,
    val start: String,
    val end: String,
    val _id: String = ""
)
