package org.rbleuse.betterreadswebappkotlin.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SearchResult(
    @JsonProperty("docs")
    val books: List<SearchResultBook> = emptyList()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SearchResultBook(
    val key: String,
    val title: String = "",
    @JsonProperty("author_name")
    val authors: List<String> = emptyList(),
    @JsonProperty("cover_i")
    val cover: String = "",
    @JsonProperty("first_publish_year")
    val publishedYear: Int = 0
)
