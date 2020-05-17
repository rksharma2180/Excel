package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
class Holiday(
        var name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
