package org.rbleuse.betterreadswebappkotlin.domain

import org.rbleuse.betterreadswebappkotlin.domain.pk.UserBooksPk
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.CassandraType.Name
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.time.LocalDate

@Table("book_by_user_and_bookid")
data class UserBooks(
    @PrimaryKey
    val userBooksPk: UserBooksPk,

    @get:Column("started_date")
    @CassandraType(type = Name.DATE)
    val startedDate: LocalDate,

    @get:Column("complete_date")
    @CassandraType(type = Name.DATE)
    val completedDate: LocalDate,

    @get:Column("reading_status")
    @CassandraType(type = Name.TEXT)
    val readingStatus: String,

    @get:Column("rating")
    @CassandraType(type = Name.INT)
    val rating: Int
)
