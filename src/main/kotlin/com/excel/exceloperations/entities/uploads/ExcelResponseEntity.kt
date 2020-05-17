package com.excel.exceloperations.entities.uploads

open class ExcelResponseEntity(
        val errors: MutableList<String> = mutableListOf(),
        var status: String? = null,
        open val rowIndex: Int? = null
        )