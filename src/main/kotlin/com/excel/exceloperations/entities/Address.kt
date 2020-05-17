package com.excel.exceloperations.entities

class Address(
        var id: Long? = null,
        var houseNumber: String,
        var streetName: String,
        var landMark: String,
        var city: String,
        var state: String,
        var addressType: String,
        var zipCode: String,
        var isActive: Boolean,
        var userId: Long
)