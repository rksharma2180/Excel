package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "employees")
class Employee(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var name: String
)
