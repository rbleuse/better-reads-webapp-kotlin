package org.rbleuse.betterreadswebappkotlin.repository

import org.rbleuse.betterreadswebappkotlin.domain.Author
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : CassandraRepository<Author, String>
