package org.rbleuse.betterreadswebappkotlin.controller

import org.rbleuse.betterreadswebappkotlin.dto.SearchResult
import org.rbleuse.betterreadswebappkotlin.dto.SearchResultBook
import org.rbleuse.betterreadswebappkotlin.getCoverLink
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Controller
class SearchController
constructor(private val webClient: WebClient) {

    @GetMapping("/search")
    fun searchResults(@RequestParam query: String, model: Model): String {
        val mono = webClient.get().uri("?q={query}", query).retrieve().bodyToMono<SearchResult>()
        val searchResults = mono.block()?.books?.stream()?.limit(10)?.toList().orEmpty()

        val results = searchResults.map {
            SearchResultBook(
                it.key.removePrefix("/works/"),
                it.title,
                it.authors,
                it.cover.getCoverLink(),
                it.publishedYear
            )
        }
        println(results)
        model.addAttribute("searchResults", results)
        return "search"
    }
}
