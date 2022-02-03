package org.rbleuse.betterreadswebappkotlin.controller

import org.rbleuse.betterreadswebappkotlin.repository.BookRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class BookController
constructor(private val bookRepository: BookRepository) {

    private val coverImageRoot = "http://covers.openlibrary.org/b/id"

    @GetMapping("/books/{bookId}")
    fun getBook(@PathVariable("bookId") bookId: String, model: Model): String {
        val book = bookRepository.findByIdOrNull(bookId)
        if (null != book) {
            val coverImageUrl = if (book.coverIds.isNotEmpty()) {
                "$coverImageRoot/${book.coverIds.first()}-L.jpg"
            } else {
                "no_image.png"
            }
            model.addAttribute("coverImage", coverImageUrl)
            model.addAttribute("book", book)
            return "book"
        }

        return "book-not-found"
    }
}
