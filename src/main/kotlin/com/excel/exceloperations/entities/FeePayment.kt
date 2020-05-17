package com.excel.exceloperations.entities

import java.sql.Timestamp

class FeePayment (
        val id: Long,
        val amount: Int,
        val termId: Long,
        val number: String,
        val mode: String? = null,
        val ddChequeNumber: String? = null,
        val paidAt: Timestamp? = null
)