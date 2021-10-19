package ru.sber.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import ru.sber.services.FirstService
import ru.sber.services.SecondService

@Configuration
@ComponentScan("ru.sber.services")
class ServicesConfig{
    @Bean
    fun single(): FirstService{
        return FirstService()
    }
    @Bean
    fun prototype(): SecondService{
        return SecondService()
    }
}
