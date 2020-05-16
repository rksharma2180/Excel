package com.excel.exceloperations.entities

import java.io.Serializable

class Section(
        var id: Long? = null,
        var name: String,
        var standard: Class? = null,
        var classId: Long? = null
) : Serializable
