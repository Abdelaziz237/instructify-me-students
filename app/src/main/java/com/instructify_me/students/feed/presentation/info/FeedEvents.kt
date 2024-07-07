package com.instructify_me.students.feed.presentation.info

sealed class FeedEvents {
    object OpenAddCommentDialog : FeedEvents()
    object AddComment : FeedEvents()
    object DismissAddCommentDialog : FeedEvents()
    object OpenPostDialog : FeedEvents()
    object AddPost : FeedEvents()
    object DismissPostDialog : FeedEvents()
}