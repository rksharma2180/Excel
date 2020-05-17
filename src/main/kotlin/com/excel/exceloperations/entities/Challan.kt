//package com.urskool.entities
//
//import com.excel.exceloperations.entities.ChallanItem
//import com.fasterxml.jackson.annotation.JsonIgnore
//import com.urskool.enums.Status
//import java.sql.Timestamp
//
//class Challan(
//        var id: Long? = -1,
//        var number: String = "",
//        var issuedAt: Timestamp,
//        val type: String = "challan", // TODO make it mode
//        var status: String = Status.REQUESTED.toString(),
//        var enrolmentId: Long,
//        var termId: Long? = null,
//
//        var enrolment: Enrolment? = null,
//        var challanItems: MutableList<ChallanItem> = mutableListOf(),
//
//        var paybleAmout: Int = 0,
//        var mode: String = "",
//        var paidAt: Timestamp? = null,
//        var ddChequeNumber: String? = null
//) {
//    @JsonIgnore
//    fun getNamedParametersMap(): Map<String, Any?> {
//        val map = hashMapOf<String, Any?>()
//        map["number"] = this.number
//        map["type"] = this.type
//        map["issued_at"] = this.issuedAt
//        map["status"] = this.status
//        map["enrolment_id"] = this.enrolmentId
//        map["term_id"] = this.termId
//        map["mode"] = this.mode
//        map["paid_at"] = this.paidAt
//        map["dd_cheque_number"] = this.ddChequeNumber
//        return map
//    }
//}