package com.instructify_me.students.portal.presentation.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instructify_me.students.core.domain.model.ValidationStatus
import com.instructify_me.students.core.presentation.InstructifyMeApp.Companion.appModule
import com.instructify_me.students.portal.data.repository.TutorsRepositoryImpl
import com.instructify_me.students.portal.domain.usecases.GetTutorsUseCase
import com.instructify_me.students.portal.domain.utils.parseJson
import com.instructify_me.students.portal.domain.utils.readJsonFromAssets
import com.instructify_me.students.portal.domain.utils.toTutorsState
import com.instructify_me.students.portal.presentation.info.PortalEvents
import com.instructify_me.students.portal.presentation.info.PortalState
import com.instructify_me.students.portal.presentation.info.TutorState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PortalViewModel: ViewModel() {

    private var _tutors = listOf<TutorState>()
//        TutorState(
//            name = "Youssef Wahba",
//            jobTitle = "DevOps Engineer",
//            jobSite = "Google",
//            isAvailable = true,
//            tags = listOf(
//                "JavaScript",
//                "NodeJS",
//                "Python",
//                "C++",
//                "C#",
//                "Java",
//                "Go",
//                "HTML",
//                "CSS"
//            ),
//            bio = "I`m software engineer graduated from faculty of computers and artificial intelligence cairo university , have strong knowledge on programming languages , develop and deploy web application , and high software skills",
//            sessionFees = "500",
//            similarity = 2.0,
//        ),
//        TutorState(
//            name = "Rahma Sanad",
//            jobTitle = "Backend Engineer",
//            jobSite = "IBM",
//            isAvailable = true,
//            tags = listOf(
//                "JavaScript",
//                "NodeJS",
//                "Ruby",
//                "C++",
//                "C#",
//                "Rust",
//            ),
//            bio = "I`m software engineer graduated from faculty of computers and artificial intelligence cairo university , have strong knowledge on programming languages , develop and deploy web application , and high software skills",
//            sessionFees = "500",
//            similarity = 2.0,
//        )
//    )

//    private val portalState = PortalState(
//        tags = listOf(
//            "Python",
//            "JavaScript",
//            "Java",
//            "C++",
//            "Ruby",
//            "PHP",
//            "Swift"
//        ),
//        tutors = _tutors
//    )

    private val _state = mutableStateOf(PortalState())
    val state: State<PortalState> = _state


    private lateinit var getTutor: GetTutorsUseCase

    init {
        viewModelScope.launch {
            getTutor = GetTutorsUseCase(TutorsRepositoryImpl(authToken = appModule.userClient.getAuthToken()))
        }
    }

    fun onEvent(event: PortalEvents) {
        when (event) {
            is PortalEvents.LoadTags -> {
                viewModelScope.launch {
                    val jsonString = readJsonFromAssets(event.context, "tags.json")
                    if (jsonString != null) {
                        val categories = parseJson(jsonString)
                        val tags = mutableListOf<String>()

                        categories.forEach {
                            tags.addAll(it.tags.subList(0, 5).map { it.lowercase() })
                        }

                        _state.value = state.value.copy(
                            tags = tags
                        )
                    }
                }
            }

            PortalEvents.LoadTutors -> {
                viewModelScope.launch {
                    when(val result = getTutor()) {
                        is ValidationStatus.Valid -> {
                            val tutors = result.data
                            _tutors = tutors.toTutorsState()
                            _state.value = state.value.copy(
                                tutors = tutors.toTutorsState()
                            )
                        }
                        is ValidationStatus.NotValid -> {

                        }
                    }

                }
            }

            is PortalEvents.FilterByTag -> {
                val tutors = mutableListOf<TutorState>()

                _state.value.tutors.forEach {
                    if (it.tags.containsAll(event.tags)) {
                        tutors.add(it)
                    }
                }

                _state.value = state.value.copy(
                    tutors = tutors
                )
            }
        }
    }
}