package com.excel.exceloperations.entities.responses

import com.excel.exceloperations.entities.Leave
import java.sql.Date

class TodayOnLeave(
        date: Date,
        employees: List<Leave>
)