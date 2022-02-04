package org.rbleuse.betterreadswebappkotlin.dto

import java.time.LocalDate

data class UserBook(
    val userId: String? = null,
    val bookId: String? = null,
    val startedDate: LocalDate? = null,
    val completedDate: LocalDate? = null,
    val readingStatus: String? = null,
    val rating: Int? = null
)
