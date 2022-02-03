package org.rbleuse.betterreadswebappkotlin.repository

import org.rbleuse.betterreadswebappkotlin.domain.Book
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CassandraRepository<Book, String>
