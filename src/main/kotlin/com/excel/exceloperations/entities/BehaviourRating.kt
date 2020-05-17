package com.excel.exceloperations.entities

import javax.persistence.*

@Entity
@Table(name = "behaviour_ratings")
class BehaviourRating(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var name: String
) {
}