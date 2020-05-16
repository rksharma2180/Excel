package com.excel.exceloperations.services

import com.excel.exceloperations.entities.Student
import com.excel.exceloperations.repositories.StudentRepo
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

@Service
class ExcelService(private val studentRepo: StudentRepo) {

    fun processExcelRows(file: MultipartFile): List<Student> {
        val workbook = XSSFWorkbook(file.inputStream)
        val worksheet = workbook.getSheetAt(0)
        val list = ArrayList<Student>()

        for (i in 1 until worksheet.physicalNumberOfRows) {
            val row = worksheet.getRow(i)
            val s = Student(
                    null,
                    row.getCell(0).stringCellValue,
                    row.getCell(1).stringCellValue,
                    row.getCell(2).stringCellValue,
                    row.getCell(4).stringCellValue
            )
            list.add(studentRepo.save(s))
        }
        list.forEach(System.out::println)
        return list
    }

    fun generateExcel(): ByteArrayInputStream {
        val students = studentRepo.findAll()
        val columns = arrayOf("SNo.", "Admission Number", "First Name", "Last Name", "AAdhar Number", "DOB",
                "Place of Birth", "Gender", "Identification Mark 1", "Identification Mark 2", "Religion", "Blood Group",
                "Class", "Mother Tongue", "User", "Enrolment No", "Standard", "Section")
        val workbook = XSSFWorkbook()

        val outStream = ByteArrayOutputStream()

        val creationHelper = workbook.creationHelper
        val sheet = workbook.createSheet()
        val font = workbook.createFont()
        font.bold = true
        font.color = IndexedColors.BLACK.index
        val headerCellStyle = workbook.createCellStyle()
        headerCellStyle.setFont(font)

        val headerRow = sheet.createRow(0)

        for (index in 0 until columns.size) {
            val cell = headerRow.createCell(index)
            cell.setCellValue(columns[index])
            cell.cellStyle = headerCellStyle
        }
        var i = 1;
        val itr = students.iterator()
        while(itr.hasNext()) {
            val student = itr.next()
            val fieldValueMap = student.getFieldValueMap()
            val dataRow = sheet.createRow(i)
            val sNoCell = dataRow.createCell(0)
            sNoCell.setCellValue( "$i")
            for(index in 1 until columns.size) {
                val cell = dataRow.createCell(index)
                cell.setCellValue(fieldValueMap[columns[index]])
            }
            /*
            val admissionNoCell = dataRow.createCell(1)
            admissionNoCell.setCellValue(it.admissionNumber)

            val mNameCell = dataRow.createCell(2)
            mNameCell.setCellValue(it.mname)
            val lNameCell = dataRow.createCell(3)
            lNameCell.setCellValue(it.lname)*/
            i++
        }

        outStream.use {
            workbook.write(outStream)
        }

        return ByteArrayInputStream(outStream.toByteArray())
    }

    fun createCell(index: Int, value: String, row: Row) {

    }

}