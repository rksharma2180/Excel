package com.excel.exceloperations.entities.requests

import com.fasterxml.jackson.annotation.JsonIgnore
import com.excel.exceloperations.entities.Attendance
import java.io.Serializable
import java.sql.Date

class AttendanceRequest(
        var id: Long ? = -1,
        var timestamp: Date,
        var classId: Long,
        var sectionId: Long,
        var sendSms: Boolean,
        var absentees: List<Attendance>
) : Serializable
{
@JsonIgnore
    fun getNamedParametersMap(): Map<String, Any> {
        val map = hashMapOf<String, Any>()
        map["timestamp"] = this.timestamp
        map["classId"] = this.classId
        map["sectionId"] = this.sectionId
        map["sendSms"] = this.sendSms
        map["absentees"] = this.absentees
        return map
    }
}