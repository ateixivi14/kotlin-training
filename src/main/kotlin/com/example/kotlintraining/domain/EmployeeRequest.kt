package com.example.kotlintraining.domain

import java.time.LocalDate
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class EmployeeRequest(
    
    @field:NotBlank
    val name: String,
    
    @field:NotBlank
    val document: String,
   
    val startedAt: LocalDate,
   
    @field:Min(900)
    val salary: Int
) {
    @AssertTrue(message = "the started at is before today")
    fun isValidStartedAt(): Boolean = LocalDate.now().isAfter(startedAt)
}
