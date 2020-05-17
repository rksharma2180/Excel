//package com.excel.exceloperations.entities
//
//import com.fasterxml.jackson.annotation.JsonIgnore
//import com.urskool.entities.Enrolment
//import com.urskool.enums.ConcessionType
//
//data class Concession(
//        var id: Long? = null,
//        val message: String,
//        val amount: Int,
//        val type: String = ConcessionType.CONCESSION.toString(),
//
//        val feeDefinitionId: Long,
//        val enrolmentId: Long,
//
//        var feeDefinition: FeeDefinition? = null,
//        var enrolment: Enrolment? = null
//) {
//    @JsonIgnore
//    fun getNamedParametersMap(): Map<String, Any> {
//        val map = hashMapOf<String, Any>()
//        map["message"] = this.message
//        map["amount"] = this.amount
//        map["type"] = ConcessionType.valueOf(this.type).toString()
//        map["fee_definition_id"] = this.feeDefinitionId
//        map["enrolment_id"] = this.enrolmentId
//
//        return map
//    }
//}