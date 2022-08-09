package com.example.kotlintraining.service

import com.example.kotlintraining.domain.EmployeeRequest
import com.example.kotlintraining.domain.EmployeeResponse
import com.example.kotlintraining.entity.Employee
import com.example.kotlintraining.repository.EmployeeRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository
) {
    private val logger = LoggerFactory.getLogger(javaClass)
   
    // Any RuntimeException does rollback in transaction.
    @Transactional
    fun create(request: EmployeeRequest): Long{
        if (employeeRepository.existsByDocument(request.document)) {
            throw RuntimeException("Document already exists")
        }
        return employeeRepository.save(request.toEntity())
            .also {
                logger.info("saved employee $it")
            }.id
    }
    
    @Transactional(readOnly = true)
    fun findAll(): List<EmployeeResponse> {
        return employeeRepository.findAll().map{
           it.toResponse()
        }
    }
    
}

private fun Employee.toResponse(): EmployeeResponse = 
    EmployeeResponse(
        id, name, startedAt, endedAt, document, salary
    )

private fun EmployeeRequest.toEntity(): Employee = Employee(
    name =  this.name,
    document = this.document,
    salary = this.salary,
    startedAt = this.startedAt
)