//package com.excel.exceloperations.entities.requests
//
//import com.urskool.enums.Status
//import java.sql.Date
//import java.sql.Timestamp
//import java.util.*
//import kotlin.collections.HashMap
//
//class ChallanRequest(
//        var id: Long? = null,
//        var number: String = "",
//        var createdAt: Timestamp = Timestamp(Calendar.getInstance().timeInMillis),
//        var status: String = Status.REQUESTED.toString(),
//        var enrolmentId: Long,
//        var termId: Long,
//
//        var paymentMode: Map<String, Any> = mapOf(),
//
//        var mode: String = "cash",
//        var ddChequeNumber: String = "",
//        var paidAt: Timestamp = Timestamp(Calendar.getInstance().timeInMillis),
//
//        var feeDefinitions: List<HashMap<String, Any>> = listOf()
//)