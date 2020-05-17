package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "subjects")
class Subject(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = -1,

        var name: String
) {
}
