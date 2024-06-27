package com.instructify_me.students.auth.data.source.objects

object ValidationCodes {
    const val UNAUTHORIZED: Int = 700
    const val PARSING_EXCEPTION: Int = 701
    const val ACCESS_DENIED: Int = 702
    const val WRONG_PASSWORD: Int = 703
    const val EMAIL_NOT_FOUND: Int = 704
    const val EMAIL_ALREADY_EXISTS: Int = 704
    const val BAD_NETWORK: Int = 705
    const val BAD_TRANSACTION: Int = 706
    const val UNDEFINED: Int = 888
}