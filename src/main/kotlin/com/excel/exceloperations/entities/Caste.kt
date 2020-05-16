package com.excel.exceloperations.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "castes")
class Caste(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = -1,
        var name: String

//        @OneToMany(mappedBy = "caste", fetch = FetchType.LAZY)
//        @JsonIgnore
//        val castes: MutableList<Caste> = mutableListOf(),
//
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "caste_id", nullable = true)
//        @OnDelete(action = OnDeleteAction.CASCADE)
//        val caste: Caste? = null
) {
}