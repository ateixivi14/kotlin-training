package com.example.kotlintraining.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Version

@Entity
data class Employee(
    @Id
    @GeneratedValue
    val id: Long = 0L,
    
    val name: String,
    
    val startedAt: LocalDate,
    
    val endedAt: LocalDate? = null,
    
    val document: String,
    
    val salary: Int, 
    
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    @UpdateTimestamp
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    
    @Version
    val version: Long = 1L
    
)
