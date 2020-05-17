package com.excel.exceloperations.entities.requests

import java.sql.Time

class PeriodRequest (
        val startAt: Time,
        val stopAt: Time,
        val teacherId: Long,
        val sectionId: Long,
        val subjectId: Long,
        val isBreak: Boolean = false
) {
}