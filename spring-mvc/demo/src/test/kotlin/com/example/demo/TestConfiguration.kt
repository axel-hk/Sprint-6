package com.example.demo

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration
class TestConfiguration {

    @Bean
    fun testRestTemplate(): TestRestTemplate = TestRestTemplate()
}