package com.excel.exceloperations.entities

import java.io.Serializable

class SMSResponse(
        val JobId: String,
        val Ack: String,
        var NoOfSMS: Int
) : Serializable