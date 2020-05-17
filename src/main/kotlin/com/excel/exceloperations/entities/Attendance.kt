package com.excel.exceloperations.entities

import java.sql.Date
import java.util.*

class Attendance(
        var enrolmentId: Long,
        var reason: String = "",
        var  present: Boolean = true,
        var createdOn: Date = Date(Calendar.getInstance().timeInMillis)
)