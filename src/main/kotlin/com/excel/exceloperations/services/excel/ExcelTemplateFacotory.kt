package com.excel.exceloperations.services.excel

import org.springframework.stereotype.Component

@Component
class ExcelTemplateFactory {

    fun getInstance(templateType: String): ExcelTemplate {
        return when (templateType) {
            "student" -> StudentExcelTemplateService()
            "staff" -> StaffExcelTemplateService()
            else -> throw Exception("Template type $templateType not supported.")
        }
    }
}