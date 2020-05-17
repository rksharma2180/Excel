package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "profiles")
class Profile(
        var name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
