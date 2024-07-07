package com.instructify_me.students.portal.presentation.info

import android.content.Context

sealed class PortalEvents {
    data class LoadTags(val context: Context) : PortalEvents()
    object LoadTutors : PortalEvents()
    data class FilterByTag(val tags: List<String>) : PortalEvents()
}