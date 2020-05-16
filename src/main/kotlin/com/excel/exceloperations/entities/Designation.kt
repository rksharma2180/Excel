package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "designations")
class Designation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var name: String
) {
}