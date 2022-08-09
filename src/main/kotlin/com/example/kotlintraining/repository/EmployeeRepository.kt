package com.example.kotlintraining.repository

import com.example.kotlintraining.entity.Employee
import org.springframework.data.repository.CrudRepository

interface EmployeeRepository : CrudRepository<Employee, Long> {
    fun existsByDocument(document: String): Boolean
}