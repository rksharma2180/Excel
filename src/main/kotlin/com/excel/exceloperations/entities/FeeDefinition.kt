package com.excel.exceloperations.entities

class FeeDefinition(
        var id: Long? = -1,
        var name: String,
        var amount: Int,

        var classId: Long? = null,
        var sectionId: Long? = null,
        var feeTypeId: Long? = null,
        var termId: Long? = null,

        val standard: Class? = null,
        val section: Section? = null,
        var feeType: FeeType? = null
) {
}