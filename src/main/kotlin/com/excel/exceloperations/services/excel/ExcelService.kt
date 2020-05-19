package com.excel.exceloperations.services.excel

import com.excel.exceloperations.entities.uploads.ExcelResponseEntity
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

@Service
class ExcelService(
        private val excelTemplateFactory: ExcelTemplateFactory
) {
    fun processExcel(file: MultipartFile, templateType: String): List<ExcelResponseEntity<Any>> {
        val workbook = XSSFWorkbook(file.inputStream)
        return excelTemplateFactory.getInstance(templateType).processExcelRecords(workbook)
    }

    fun generateTemplate(templateType: String): ByteArrayInputStream {
        val outStream = ByteArrayOutputStream()
        val workbook: XSSFWorkbook?
        workbook = excelTemplateFactory.getInstance(templateType).generateWorkbookTemplate()

        outStream.use {
            workbook.write(outStream)
        }
        return ByteArrayInputStream(outStream.toByteArray())
    }
}
