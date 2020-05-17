package com.excel.exceloperations.controllers

import com.excel.exceloperations.entities.uploads.ExcelResponseEntity
import com.excel.exceloperations.services.excel.ExcelService
import org.springframework.beans.factory.annotation.Required
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("excel")
class ExcelCtrl(private val excelService: ExcelService) {

    @PostMapping("/import/{templateType}")
    fun import(@PathVariable("templateType") templateType: String,
               @RequestParam("file") file: MultipartFile) : List<ExcelResponseEntity> {
        return excelService.processStudentExcelRows(file, templateType)
    }

    @GetMapping("/downloadTemplate/{templateType}")
    fun generateExcel(@PathVariable("templateType") templateType: String): ResponseEntity<InputStreamResource> {
        val inputStream  = excelService.generateExcelTemplate(templateType);
        val header = HttpHeaders()
        header.add("Content-Disposition", "attachment; filename=demo.xlsx")
        return ResponseEntity.ok().headers(header).body(InputStreamResource(inputStream))
    }
}