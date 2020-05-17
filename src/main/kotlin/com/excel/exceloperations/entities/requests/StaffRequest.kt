package com.excel.exceloperations.entities.requests

import java.sql.Date

class StaffRequest (
        var firstName: String,
        var lastName: String,
        val panCardNumber: String,
        val aadharNumber: String,
        val maritalStatus: String,
        val marriedOn: Date? = null,
        val dob: Date,
        val placeOfBirth: String,
        val gender: String,
        val phone: String,
        val email: String,

        val bloodGroupId: Long? = null,
        val motherTongueId: Long? = null,
        val religionId: Long? = null
)