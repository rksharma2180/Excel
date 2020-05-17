package com.excel.exceloperations.entities

import com.fasterxml.jackson.annotation.JsonIgnore

class ChallanItem(
        var challanId: Long,
        var feeDefinitionId: Int,
        var amount: Int,
        var concessionId: Long? = null,
        var concessionAmount: Int = 0
) {
    @JsonIgnore
    fun getNamedParametersMap(): Map<String, Any?> {
        val map = hashMapOf<String, Any>()
        map["challan_id"] = this.challanId
        map["fee_definition_id"] = this.feeDefinitionId
        map["amount"] = this.amount
        map["concession_id"] = this.concessionId ?: Any()
        return map
    }
}