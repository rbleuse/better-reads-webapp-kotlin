package org.rbleuse.betterreadswebappkotlin.controller

import org.rbleuse.betterreadswebappkotlin.domain.UserBooks
import org.rbleuse.betterreadswebappkotlin.domain.pk.UserBooksPk
import org.rbleuse.betterreadswebappkotlin.repository.UserBooksRepository
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.servlet.ModelAndView
import java.time.LocalDate

@Controller
class UserBooksController
constructor(private val userBooksRepository: UserBooksRepository) {

    @PostMapping("/addUserBook")
    fun addBookToUser(@RequestBody formData: MultiValueMap<String, String>, @AuthenticationPrincipal principal: OAuth2User): ModelAndView? {
        val login = principal.getAttribute<String>("login") ?: return null

        val bookId = formData.getFirst("bookId")!!
        val userBook = UserBooks(
            UserBooksPk(login, bookId),
            LocalDate.parse(formData.getFirst("startDate")),
            LocalDate.parse(formData.getFirst("completedDate")),
            formData.getFirst("readingStatus")!!,
            formData.getFirst("rating")!!.toInt()
        )
        userBooksRepository.save(userBook)

        return ModelAndView("redirect:/books/$bookId")
    }
}
