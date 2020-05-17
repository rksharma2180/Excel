package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "teachers")
class Teacher(
        var name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
