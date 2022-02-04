package org.rbleuse.betterreadswebappkotlin.configuration

import com.datastax.oss.driver.api.core.CqlSessionBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import java.nio.file.Path

@Configuration
class ApplicationConfiguration {

    @Bean
    fun objectMapper(): ObjectMapper = jacksonObjectMapper()

    /**
     * This is necessary to have the Spring Boot app use the Astra secure bundle
     * to connect to the database
     */
    @Bean
    fun sessionBuilderCustomizer(astraProperties: DataStaxAstraProperties): CqlSessionBuilderCustomizer {
        val bundle: Path = astraProperties.secureConnectBundle.toPath()
        return CqlSessionBuilderCustomizer { builder: CqlSessionBuilder -> builder.withCloudSecureConnectBundle(bundle) }
    }

    @Bean
    fun webClient(webClientBuilder: WebClient.Builder): WebClient =
        webClientBuilder.baseUrl("http://openlibrary.org/search.json").build()
}
