package com.excel.exceloperations.entities

import java.sql.Date

class FamilyMember(
        var id: Long? = null,
        var firstName: String,
        var lastName: String,
        var relation: String,
        var qualification: String? = "",
        var designation: String? = "",
        var aadharNumber: String? = "",
        var gender: String? = "",
        var dob: Date? = null,
        var userId: Long
)