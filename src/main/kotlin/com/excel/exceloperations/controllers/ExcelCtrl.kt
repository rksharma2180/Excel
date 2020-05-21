package com.excel.exceloperations.controllers

import com.excel.exceloperations.entities.uploads.ExcelResponseEntity
import com.excel.exceloperations.services.excel.ExcelService
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("excel")
class ExcelCtrl(private val excelService: ExcelService) {

    @PostMapping("/import/{templateType}")
    fun import(@PathVariable("templateType") templateType: String,
               @RequestParam("file") file: MultipartFile): ResponseEntity<List<ExcelResponseEntity<Any>>> {
        return try {
            ResponseEntity(excelService.processExcel(file, templateType), HttpStatus.OK)
        } catch(ex: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/downloadTemplate/{templateType}")
    fun generateExcel(@PathVariable("templateType") templateType: String): ResponseEntity<InputStreamResource> {
        return try {
            val inputStream  = excelService.generateTemplate(templateType);
            val header = HttpHeaders()
            header.add("Content-Disposition", "attachment; filename=$templateType.xlsx")
            ResponseEntity.ok().headers(header).body(InputStreamResource(inputStream))
        } catch (ex: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}