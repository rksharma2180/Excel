package com.excel.exceloperations.controllers

import com.excel.exceloperations.services.pdf.PDFService
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("pdf")
class PDFCtrl(private val pdfService: PDFService) {

    @GetMapping("/downloadPDF")
    fun getPDFDoc(): ResponseEntity<InputStreamResource> {
        return try {
            val inputStream  = pdfService.generatePDF()
            val header = HttpHeaders()
            header.add("Content-Disposition", "attachment; filename=doc.pdf")
            ResponseEntity.ok().headers(header).body(InputStreamResource(inputStream))
        } catch (ex: Exception) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}