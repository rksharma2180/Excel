package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "blood_groups")
class BloodGroup(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var name: String
) {
}