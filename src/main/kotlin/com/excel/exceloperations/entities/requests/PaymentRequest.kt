//package com.excel.exceloperations.entities.requests
//
//import com.urskool.enums.Status
//import java.sql.Timestamp
//import java.util.*
//import kotlin.collections.HashMap
//
//class PaymentRequest(
//        var id: Long? = null,
//        var number: String = "",
//        var createdAt: Timestamp = Timestamp(Calendar.getInstance().timeInMillis),
//        var status: String = Status.REQUESTED.toString(),
//        var enrolmentId: Long,
//        var mode: String = "online",
//        var ddChequeNumber: String = "",
//        var paidAt: Timestamp = Timestamp(Calendar.getInstance().timeInMillis),
//
//        var feeDefinitions: List<HashMap<String, Any>> = listOf()
//)