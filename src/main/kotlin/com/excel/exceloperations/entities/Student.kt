package com.excel.exceloperations.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name="students")
data class Student(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var admissionNumber: String? = null,
        var aadharNumber: String? = null,
        var dob: Date? = null,
        var placeOfBirth: String? = null,
        var gender: String? = null,
        var identificationMark_1: String? = null,
        var identificationMark_2: String? = "",
        var religionId: Long? = null,
        var bloodGroupId: Long? = null,
        var joinClassId: Long? = null,
        var motherTongueId: Long? = null,

        var user: User? = null,
        var userId: Long? = null,

        var enrolment: Enrolment? = null,
        var enrolmentId: Long? = null,

        var standard: Class? = null,
        var standardId: Long? = null,

        var section: Section? = null,
        var sectionId: Long? = null,

        var firstName: String? = "",
        var lastName: String? = ""
)