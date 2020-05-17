package com.excel.exceloperations.entities

class Fee(
        val id: Long? = null,
        var name: String = "",
        var amount: Long = 0,
        var termId: Long? = null,
        var isService: Boolean = false
)
