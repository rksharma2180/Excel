package com.excel.exceloperations.entities

import java.util.*

class Term(
        var id: Long? = -1,
        var name: String,
        var startAt: Date,
        var endAt: Date,
        var type: String,
        var academicYearId: Long,
        val academicYear: AcademicYear? = null
)