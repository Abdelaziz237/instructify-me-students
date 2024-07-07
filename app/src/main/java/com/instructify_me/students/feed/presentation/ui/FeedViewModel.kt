package com.instructify_me.students.feed.presentation.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.instructify_me.students.feed.presentation.info.FeedEvents
import com.instructify_me.students.feed.presentation.info.FeedState

class FeedViewModel: ViewModel() {


    private val _state = mutableStateOf(FeedState())
    val state: State<FeedState> = _state


    fun onEvent(event: FeedEvents) {
        when (event) {
            FeedEvents.OpenAddCommentDialog -> {
                _state.value = state.value.copy(
                    isAddCommentDialogVisible = true
                )
            }

            FeedEvents.AddComment -> {

            }

            FeedEvents.DismissAddCommentDialog -> {
                _state.value = state.value.copy(
                    isAddCommentDialogVisible = false
                )
            }

            FeedEvents.OpenPostDialog -> {
                _state.value = state.value.copy(
                    isPostDialogVisible = true
                )
            }

            FeedEvents.AddPost -> {

            }

            FeedEvents.DismissPostDialog -> {
                _state.value = state.value.copy(
                    isPostDialogVisible = false
                )
            }

        }
    }
}