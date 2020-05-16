package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "religions")
class Religion(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var name: String
)