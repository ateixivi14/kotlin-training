package com.example.kotlintraining.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Controller
@Configuration
class SwaggerConfiguration {
    
    @GetMapping("/")
    fun index() : String = " redirect:swagger-ui/index.html"
}