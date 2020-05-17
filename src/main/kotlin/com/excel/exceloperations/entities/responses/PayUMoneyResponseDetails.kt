package com.excel.exceloperations.entities.responses

class PayUMoneyResponseDetails(
        var mihpayid: String,
        var mode: String,
        var status: String,
        var key: String,
        var txnid: String,
        var amount: String,
        var hash: String,

        var discount: String? = null,
        var productinfo: String, // List<PayuPaymentPart> = emptyList()
        var firstname: String? = null,
        var lastname: String? = null,
        var city: String? = null,
        var state: String? = null,
        var country: String? = null,
        var email: String? = null,
        var phone: String? = null,
        var udf1: String? = null,

        var PG_TYPE: String? = null,
        var bank_ref_num: String? = null,
        var payuMoneyId: String? = null,
        var additionalCharges: String? = null
)