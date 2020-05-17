package com.excel.exceloperations.entities

class PayuRequest(
        var key: String,
        var amount: String,
        var firstname: String,
        var email: String,
        var phone: String,
        var productinfo: String,
        var surl: String,
        var furl: String,
        var service_provider: String,

        var txnid: String,

        var lastname: String = "",
        var curl: String = "",
        var address1: String = "",
        var address2: String = "",
        var city: String = "",
        var state: String = "",
        var country: String = "",
        var zipcode: String = "",
        var pg: String = "",

        var udf1: String = "",
        var udf2: String = "",
        var udf3: String = "",
        var udf4: String = "",
        var udf5: String = "",
        var udf6: String = "",
        var udf7: String = "",
        var udf8: String = "",
        var udf9: String = "",
        var udf10: String = "",
        var udf11: String = ""
) {
}