package com.instructify_me.students.portal.domain.utils

import com.instructify_me.students.portal.data.dto.TutorsResponseDTO
import com.instructify_me.students.portal.presentation.info.TutorState

fun TutorsResponseDTO.toTutorsState(): List<TutorState> {
	return this.instructors.map { tutor ->
		TutorState(
			name = tutor.name,
			jobTitle = tutor.experience[0].position,
			jobSite = tutor.experience[0].site,
			bio = tutor.bio,
			isAvailable = tutor.availabilities?.isNotEmpty() ?: false,
			tags = tutor.skills.map { it.tag },
			sessionFees = tutor.price_hour.toString(),
			similarity = 0.9,
			availability = false
		)
	}
}