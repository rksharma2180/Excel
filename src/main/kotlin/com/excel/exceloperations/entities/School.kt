package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "schools")
class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
