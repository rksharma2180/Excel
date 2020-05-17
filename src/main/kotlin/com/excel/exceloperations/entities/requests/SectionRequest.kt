package com.excel.exceloperations.entities.requests

import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("section")
class SectionRequest(
        val name: String,
        val classId: Long
) {
}
