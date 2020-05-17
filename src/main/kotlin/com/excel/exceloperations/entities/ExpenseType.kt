package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "expense_types")
class ExpenseType(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var name: String
) {
}