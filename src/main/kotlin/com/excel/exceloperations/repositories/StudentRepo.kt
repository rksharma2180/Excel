package com.excel.exceloperations.repositories

import com.excel.exceloperations.entities.Student
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepo:  CrudRepository<Student, Long>