package com.excel.exceloperations.entities

import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.*
import kotlin.collections.LinkedHashMap

@Entity
@Table(name = "students")
data class Student(
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", updatable = false, nullable = true)
        var id: Long? = null,
        
        var admissionNumber: String,
        var aadharNumber: String,
        var dob: Date? = null,
        var placeOfBirth: String? = null,
        var gender: String? = null,
        var identificationMark_1: String,
        var identificationMark_2: String? = "",
        var religionId: Long? = null,
        var bloodGroupId: Long? = null,
        var joinClassId: Long? = null,
        var motherTongueId: Long? = null,

        var user: User? = null,
        var userId: Long? = null,

        //var enrolment: Enrolment? = null,
        var enrolmentId: Long? = null,

        //var standard: Class? = null,
        var standardId: Long? = null,

        //var section: Section? = null,
        var sectionId: Long? = null,

        var firstName: String? = "",
        var lastName: String? = ""
) {
        public fun getFieldValueMap(): LinkedHashMap<String, String> {
                /*("SNo.", "Admission Number", "First Name", "Last Name", "AAdhar Number", "DOB",
                "Place of Birth", "Gender", "Identification Mark 1", "Identification Mark 2", "Religion", "Blood Group",
                "Class", "Mother Tongue", "User", "Enrolment No", "Standard", "Section")*/
                val map = LinkedHashMap<String, String>()
                map["Admission Number"] = admissionNumber
                map["First Name"] = firstName!!
                map["Last Name"] = lastName!!
                map["AAdhar Number"] = aadharNumber
                map["DOB"] = SimpleDateFormat().format(dob)
                map["Place of Birth"] = placeOfBirth!!
                map["Gender"] = gender
                map["Identification Mark 1"] = identificationMark_1
                map["Identification Mark 2"] = identificationMark_2!!
                map["Religion"] = religionId.toString()
                map["Blood Group"] = bloodGroupId.toString()

                return map
        }
}