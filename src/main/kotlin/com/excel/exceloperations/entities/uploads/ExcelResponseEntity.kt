package com.excel.exceloperations.entities.uploads

class ExcelResponseEntity<Any>(
        val errors: MutableList<String> = mutableListOf(),
        var status: String? = null,
        var rowIndex: Int? = null,
        var entity: Any? = null
)