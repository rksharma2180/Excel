package com.excel.exceloperations.entities.requests

import com.fasterxml.jackson.annotation.JsonRootName
import java.sql.Date

@JsonRootName("student")
class StudentRequest(
        var firstName: String,
        var lastName: String,
        val admissionNumber: String,
        val aadharNumber: String,
        val dob: Date,
        val placeOfBirth: String,
        val gender: String,
        val identificationMark_1: String,
        val identificationMark_2: String?,
        val phone: String,
        val email: String,

        val bloodGroupId: Long,
        val joiningClassId: Long,
        val motherTongueId: Long,
        val religionId: Long,
        val userId: Long,
        val sectionId: Long
)
