package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "events")
class Event(
        var name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
