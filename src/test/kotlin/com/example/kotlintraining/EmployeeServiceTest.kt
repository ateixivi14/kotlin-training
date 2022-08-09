package com.example.kotlintraining

import com.example.kotlintraining.domain.EmployeeRequest
import com.example.kotlintraining.entity.Employee
import com.example.kotlintraining.repository.EmployeeRepository
import com.example.kotlintraining.service.EmployeeService


import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate


class EmployeeServiceTest {
    private val employeeRepository = mockk<EmployeeRepository>()
    private val employeeService = EmployeeService(employeeRepository)

    @Test
    fun `save employee successfully`() {
        every {
            employeeRepository.existsByDocument(any())
        } returns false

        every {
            employeeRepository.save(any())
        } returns Employee(id= 11, name = "alba", document = "12345678Z", startedAt = LocalDate.now(), salary = 1000)

        val employeeRequest = EmployeeRequest(name = "alba", document = "12345678Z", startedAt = LocalDate.now(), salary = 1000)

        val response = employeeService.create(employeeRequest)

        Assertions.assertEquals(11, response)
        val documents = mutableListOf<String>()

        verify(exactly = 1) {
            employeeRepository.existsByDocument(capture(documents))
            employeeRepository.save(any())
        }
        Assertions.assertEquals(employeeRequest.document, documents.first())
    }

    @Test
    fun `saving existing document throws error`() {
        every {
            employeeRepository.existsByDocument(any())
        } returns true

        val employeeRequest = EmployeeRequest(name = "alba", document = "12345678Z", startedAt = LocalDate.now(), salary = 1000)

        val response = runCatching { employeeService.create(employeeRequest) }

        Assertions.assertTrue(response.isFailure)
        Assertions.assertTrue(response.exceptionOrNull() is RuntimeException)
        val documents = mutableListOf<String>()

        verify(exactly = 1) {
            employeeRepository.existsByDocument(capture(documents))
        }
        verify(exactly = 0) {
            employeeRepository.save(any())
        }
        Assertions.assertEquals(employeeRequest.document, documents.first())
    }
}