package com.excel.exceloperations.entities.requests

class FeeDefinitionRequest(
        val name: String,
        val classId: List<Long>,
        val amount: Int,
        val feeTypeId: Long,
        val sectionId: List<Long>,
        val termId: Long? = null
) {
}