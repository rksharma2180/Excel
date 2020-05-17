package com.excel.exceloperations.entities.requests

class AnnouncementRequest(
        type: String = "SMS", // SMS, Email, Push
        message: String,
        target: String,
        classId: Long? = null,
        sectionId: Long? = null,
        groupId: Long? = null,
        userIds: List<Long>? = listOf()
)