package com.example.kotlintraining.domain

import java.time.LocalDate

data class EmployeeResponse(
    val id: Long, 
    val name: String, 
    val startedAt: LocalDate,
    val endedAt: LocalDate?,
    val document:String,
    val salary: Int
)
