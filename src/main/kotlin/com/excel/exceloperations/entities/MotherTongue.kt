package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "mother_tongues")
class MotherTongue (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var name: String
) {
}