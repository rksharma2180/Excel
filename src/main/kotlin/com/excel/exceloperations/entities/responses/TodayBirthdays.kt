package com.excel.exceloperations.entities.responses

import com.excel.exceloperations.entities.User
import java.sql.Date

class TodayBirthdays(
        val date: Date,
        val students: List<User>,
        val staff: List<User>
)