package com.excel.exceloperations.entities

import java.sql.Date

class Staff (

        var id: Long? = null,
        var aadharNumber: String,
        var panCardNumber: String,
        var maritalStatus: String,
        var marriedOn: Date? = null,
        var dob: Date,
        var user: User? = null,
        var userId: Long,
        var addresses: List<Address>? = emptyList()
)