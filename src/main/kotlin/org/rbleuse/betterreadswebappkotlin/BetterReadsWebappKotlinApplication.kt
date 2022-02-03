package org.rbleuse.betterreadswebappkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class BetterReadsWebappKotlinApplication {

    @RequestMapping("/user")
    fun user(@AuthenticationPrincipal principal: OAuth2User): String? {
        println(principal)
        return principal.getAttribute("name")
    }
}

fun main(args: Array<String>) {
    runApplication<BetterReadsWebappKotlinApplication>(*args)
}
