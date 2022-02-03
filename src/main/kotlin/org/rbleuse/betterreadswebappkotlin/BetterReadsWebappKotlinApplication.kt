package org.rbleuse.betterreadswebappkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class BetterReadsWebappKotlinApplication

fun main(args: Array<String>) {
    runApplication<BetterReadsWebappKotlinApplication>(*args)
}
