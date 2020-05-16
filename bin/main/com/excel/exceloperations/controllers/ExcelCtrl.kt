package com.excel.exceloperations.controllers

import com.excel.exceloperations.entities.Student
import com.excel.exceloperations.services.ExcelService
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("excel")
class ExcelCtrl(private val excelService: ExcelService ) {

    @RequestMapping("/test")
    fun test(): String {
        return "hello"
    }

    @PostMapping("/import")
    fun import(@RequestParam("file") file: MultipartFile): List<Student> {
        println(file.originalFilename)
        return excelService.processExcelRows(file)
    }

    @GetMapping("/downloadExcel")
    fun generateExcel(): ResponseEntity<InputStreamResource> {
        val inputStream  = excelService.generateExcel();
        val header = HttpHeaders()
        header.add("Content-Disposition", "attachment; filename=demo.xlsx")
        return ResponseEntity.ok().headers(header).body(InputStreamResource(inputStream))
    }
}