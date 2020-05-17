package com.excel.exceloperations.services.excel

import com.excel.exceloperations.entities.uploads.ExcelResponseEntity
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*

@Service
class ExcelService(
        private val studentExcelTemplate: StudentExcelTemplateService
) {
    fun processStudentExcelRows(file: MultipartFile, templateType: String): List<ExcelResponseEntity> {
        val workbook = XSSFWorkbook(file.inputStream)
        var list: List<ExcelResponseEntity> = Collections.emptyList()
        if (templateType == "student") {
            list = studentExcelTemplate.processExcelRecords(workbook)
        } else if (templateType == "staff") {
            list = studentExcelTemplate.processExcelRecords(workbook)
        }
        return list
    }

    fun generateExcelTemplate(templateType: String): ByteArrayInputStream {
        val outStream = ByteArrayOutputStream()
        var workbook: XSSFWorkbook? = null
        if (templateType == "student") {
            workbook = studentExcelTemplate.generateWorkbookTemplate()
        } else if (templateType == "staff") {
            //workbook = studentExcelTemplate.generateWorkbookTemplate()
        }
        outStream.use {
            workbook?.write(outStream)
        }
        return ByteArrayInputStream(outStream.toByteArray())
    }
}
