package com.example.kotlintraining.controller

import com.example.kotlintraining.domain.EmployeeRequest
import com.example.kotlintraining.domain.EmployeeResponse
import com.example.kotlintraining.entity.Employee
import com.example.kotlintraining.service.EmployeeService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employees")
class EmployeeController (
    private val employeeService: EmployeeService){
    
    private val logger = LoggerFactory.getLogger(javaClass)
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: EmployeeRequest): Long {
        logger.info("request $request")
        return employeeService.create(request)
    }
    
    @GetMapping
    fun listAll(): List<EmployeeResponse> {
        return employeeService.findAll();
    }
}