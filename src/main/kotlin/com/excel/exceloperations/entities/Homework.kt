package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "home_works")
class Homework(
        var name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
