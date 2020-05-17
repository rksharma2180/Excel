package com.urskool.entities

import com.excel.exceloperations.entities.Section
import com.excel.exceloperations.entities.User
import java.io.Serializable
import java.util.*

class Enrolment(
        var id: Long? = null,
        val enrolledAt: Date = Date(),
        val updatedAt: Date = Date(),

        val user: User? = null,
        val section: Section? = null,
        var sectionId: Long,
        var userId: Long
) : Serializable