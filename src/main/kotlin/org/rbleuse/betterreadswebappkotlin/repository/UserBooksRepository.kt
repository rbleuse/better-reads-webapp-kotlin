package org.rbleuse.betterreadswebappkotlin.repository

import org.rbleuse.betterreadswebappkotlin.domain.UserBooks
import org.rbleuse.betterreadswebappkotlin.domain.pk.UserBooksPk
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface UserBooksRepository : CassandraRepository<UserBooks, UserBooksPk>
