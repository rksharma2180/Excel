package com.excel.exceloperations.entities

class Qualification(
        var id: Long? = null,
        var title: String,
        var institute: String,
        var board: String,
        var yearOfPassing: Int,
        var percentage: Int,
        var division: String,
        var userId: Long
)