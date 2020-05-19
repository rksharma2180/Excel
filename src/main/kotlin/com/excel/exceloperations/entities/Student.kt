package com.excel.exceloperations.entities

import com.urskool.entities.Enrolment
import java.util.Date
import java.text.SimpleDateFormat
import javax.persistence.*

@Entity
@Table(name="Student")
class Student(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        var enrolment: Enrolment? = null,
        var enrolmentId: Long? = null,

        var standard: Class? = null,
        var standardId: Long? = null,

        var section: Section? = null,
        var sectionId: Long? = null,

        var firstName: String? = "",
        var lastName: String? = "",
        var joiningDate: Date? = null,
        var isNew: Boolean? = null
)

{
        fun getStudentFieldMap(): Map<String, String> {
                val studentMap = mutableMapOf<String, String>()

                studentMap["Name"] = "$firstName $lastName"
                studentMap["Aadhar Number"] = aadharNumber
                studentMap["Mobile Number"] = user?.primaryMobile!!
                studentMap["Secondary mobile"] = ""
                studentMap["Admission Number"] = admissionNumber
                studentMap["Father Name"] = "Father"
                studentMap["Mother Name"] = "Mother"
                studentMap["Date of Birth"] = SimpleDateFormat("dd/MM/yyyy").format(dob)
                studentMap["Admission Date"] = SimpleDateFormat("dd/MM/yyyy").format(joiningDate)
                studentMap["Class of Admission"] = "Class 12"
                studentMap["Gender"] = gender!!
                studentMap["New"] = isNew.toString()
                studentMap["Flat No."] = "#123"
                studentMap["Street Name"] = "Street"
                studentMap["Landmark"] = "Landmark"
                studentMap["City"] = "JBP"
                studentMap["State"] = "MP"

                return studentMap
        }

}

