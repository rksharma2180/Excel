package com.excel.exceloperations.entities

class FeeCollectionRequest(
        val enrolmentId: Long? = null,
        val fees: List<Fee> = listOf()
)