package com.excel.exceloperations.entities

import java.io.Serializable
import java.sql.Date

class AcademicYear(
        var id: Long? = -1,
        var title: String? = null,
        var startDate: Date? = null,
        var endDate: Date? = null,
        var isActive: Boolean = false
        // var status: Status = Status.NONE // TODO add this column in DB
) : Serializable
