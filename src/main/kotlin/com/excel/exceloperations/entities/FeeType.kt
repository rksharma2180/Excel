package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "fee_types")
class FeeType(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var name: String,
        var frequency: String
) {
}