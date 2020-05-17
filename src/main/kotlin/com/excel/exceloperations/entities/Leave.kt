package com.excel.exceloperations.entities

import com.excel.exceloperations.entities.User
import java.sql.Date

class Leave(
        var id: Long? = null,
        var userId: Long,
        var description: String = "",
        var startAt: Date,
        var endAt: Date,
        var leaveTypeId: Long,
        var user: User? = null
)