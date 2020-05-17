package com.excel.exceloperations.entities

class Announcement(
        var id: Long? = null,
        var message: String,
        var userIds: MutableList<Long> = mutableListOf()
)