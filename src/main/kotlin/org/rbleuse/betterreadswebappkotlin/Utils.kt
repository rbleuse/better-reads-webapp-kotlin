package org.rbleuse.betterreadswebappkotlin

private val coverImageRoot = "http://covers.openlibrary.org/b/id"

fun String.getCoverLink(): String {
    return "$coverImageRoot/$this-L.jpg"
}
