package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "parents")
class Parent(
        var name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
