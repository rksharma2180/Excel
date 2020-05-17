package com.excel.exceloperations.entities.responses

import com.excel.exceloperations.entities.Attendance

class AttendanceResponse (
    var attendanceTaken: Boolean = true,
    var students: List<Attendance>,
    var date: String
)