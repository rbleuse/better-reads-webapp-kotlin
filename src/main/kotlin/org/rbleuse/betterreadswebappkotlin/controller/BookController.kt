package org.rbleuse.betterreadswebappkotlin.controller

import org.rbleuse.betterreadswebappkotlin.domain.pk.UserBooksPk
import org.rbleuse.betterreadswebappkotlin.dto.UserBook
import org.rbleuse.betterreadswebappkotlin.getCoverLink
import org.rbleuse.betterreadswebappkotlin.repository.BookRepository
import org.rbleuse.betterreadswebappkotlin.repository.UserBooksRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class BookController
constructor(private val bookRepository: BookRepository, private val userBooksRepository: UserBooksRepository) {

    @GetMapping("/books/{bookId}")
    fun getBook(@PathVariable("bookId") bookId: String, model: Model, @AuthenticationPrincipal principal: OAuth2User?): String {
        val book = bookRepository.findByIdOrNull(bookId)
        if (null != book) {
            val coverImageUrl = if (book.coverIds.isNotEmpty()) {
                book.coverIds.first().getCoverLink()
            } else {
                "/images/no-image.png"
            }
            model.addAttribute("coverImage", coverImageUrl)
            model.addAttribute("book", book)

            val loginId = principal?.getAttribute<String>("login")
            loginId?.let {
                model.addAttribute("loginId", it)
                val userBookInDb = userBooksRepository.findByIdOrNull(UserBooksPk(loginId, bookId))
                val userBook = if (null != userBookInDb) {
                    UserBook(
                        userBookInDb.userBooksPk.userId,
                        userBookInDb.userBooksPk.bookId,
                        userBookInDb.startedDate,
                        userBookInDb.completedDate,
                        userBookInDb.readingStatus,
                        userBookInDb.rating,
                    )
                } else {
                    UserBook()
                }
                model.addAttribute("userBook", userBook)
            }

            return "book"
        }

        return "book-not-found"
    }
}