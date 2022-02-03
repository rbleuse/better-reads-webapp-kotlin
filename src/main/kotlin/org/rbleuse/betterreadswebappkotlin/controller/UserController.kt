package org.rbleuse.betterreadswebappkotlin.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @RequestMapping("/user")
    fun user(@AuthenticationPrincipal principal: OAuth2User): String? {
        println(principal)
        return principal.getAttribute("name")
    }
}
