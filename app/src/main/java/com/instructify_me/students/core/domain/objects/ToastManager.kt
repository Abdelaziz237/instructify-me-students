package com.instructify_me.students.core.domain.objects

import com.instructify_me.students.core.presentation.toast.ToastViewModel


object ToastManager {
    private var viewModel: ToastViewModel? = null

    fun initializeToastManager(v: ToastViewModel) {
        if (viewModel == null) {
            viewModel = v
        }
    }

    fun get(): ToastViewModel? {
        return if (viewModel != null) viewModel as ToastViewModel
        else null
    }
}