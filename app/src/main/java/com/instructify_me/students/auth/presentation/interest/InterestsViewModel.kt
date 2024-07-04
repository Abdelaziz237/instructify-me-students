package com.instructify_me.students.auth.presentation.interest

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instructify_me.students.auth.domain.AuthRepo
import com.instructify_me.students.auth.domain.usecases.SetInterestsUseCase
import com.instructify_me.students.auth.presentation.interest.info.InterestsEvent
import com.instructify_me.students.core.domain.model.ValidationStatus
import kotlinx.coroutines.launch

class InterestsViewModel: ViewModel() {
    private val _state = mutableStateOf(emptyList<String>())
    val state: State<List<String>> = _state

    private val setInterests = SetInterestsUseCase(AuthRepo.getRepo())

    fun onEvent(event: InterestsEvent, callback: () -> Unit) {
        when (event) {
            is InterestsEvent.PostInterests -> {
                viewModelScope.launch {
                    when (val result = setInterests(event.interests)) {
                        is ValidationStatus.Valid -> {
                            Log.e("tags", result.data.toString())
                        }
                        is ValidationStatus.NotValid -> {

                        }
                    }
                }
                callback()
            }
            InterestsEvent.GetInterests -> {

            }
        }
    }
}