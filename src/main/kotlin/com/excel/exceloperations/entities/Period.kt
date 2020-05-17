package com.excel.exceloperations.entities

import com.excel.exceloperations.entities.Section
import com.excel.exceloperations.entities.Subject
import com.excel.exceloperations.entities.User
import java.io.Serializable
import java.sql.Time


class Period(
        var id: Long? = -1,
        var startAt: Time,
        var stopAt: Time,
        var isBreak: Boolean,

        val teacher: User? = null,
        val section: Section? = null,
        val subject: Subject? = null
) : Serializable {
}