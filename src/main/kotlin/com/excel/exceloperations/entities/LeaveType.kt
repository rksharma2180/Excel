package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "leave_types")
class LeaveType(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var name: String
) {
}