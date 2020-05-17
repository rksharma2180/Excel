package com.excel.exceloperations.entities

import com.fasterxml.jackson.annotation.JsonIgnore

class ServiceChallanItem(
        var challanId: Long,
        var serviceId: Int,
        var amount: Int,
        var termId: Int,
        var concessionId: Int? = null
) {
    @JsonIgnore
    fun getNamedParametersMap(): Map<String, Any?> {
        val map = hashMapOf<String, Any>()
        map["challan_id"] = this.challanId
        map["service_id"] = this.serviceId
        map["amount"] = this.amount
        map["term_id"] = this.termId
        map["concession_id"] = this.concessionId ?: Any()
        return map
    }
}