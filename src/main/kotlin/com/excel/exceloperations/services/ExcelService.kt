package com.excel.exceloperations.services

import com.excel.exceloperations.entities.Student
import com.excel.exceloperations.repositories.StudentRepo
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddressList
import org.apache.poi.ss.util.CellReference
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat

@Service
class ExcelService(private val studentRepo: StudentRepo) {

    fun processExcelRows(file: MultipartFile): List<Student> {
        val workbook = XSSFWorkbook(file.inputStream)
        val worksheet = workbook.getSheetAt(1)
        val list: MutableList<Student> = mutableListOf()
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

        for (i in 1 until worksheet.physicalNumberOfRows) {
            val row = worksheet.getRow(i)
            val s = Student(
                    firstName = row.getCell(0).stringCellValue
            )
            s.firstName = row.getCell(0).stringCellValue
            s.lastName = row.getCell(1).stringCellValue
            if (row.getCell(2).cellType ==  Cell.CELL_TYPE_NUMERIC) {
                s.aadharNumber = row.getCell(2).numericCellValue.toString()
            } else {
                s.aadharNumber = row.getCell(2).stringCellValue
            }
            s.placeOfBirth = row.getCell(4).stringCellValue
            s.gender = row.getCell(5).stringCellValue
            s.identificationMark_1 = row.getCell(6).stringCellValue
            s.identificationMark_2 = row.getCell(7).stringCellValue
            s.dob = simpleDateFormat.parse(row.getCell(3).stringCellValue)
            list.add(studentRepo.save(s))
        }
        return list
    }

    fun generateStudentExcelTemplate(): ByteArrayInputStream {

        val outStream = ByteArrayOutputStream()
        val workbook = generateWorkbookTemplate()
        outStream.use {
            workbook.write(outStream)
        }

        return ByteArrayInputStream(outStream.toByteArray())
    }

    fun generateWorkbookTemplate(): XSSFWorkbook {

        val columns = arrayOf("First Name", "Last Name", "AAdhar Number", "DOB (dd/MM/yyyy)",
                "Place of Birth", "Gender", "Identification Mark 1", "Identification Mark 2", "Religion", "Blood Group",
                "Class", "Mother Tongue", "Standard")
        val classes =  arrayOf("Class 1", "Class 2", "Class 3", "Class 4", "Class 5","Class 6","Class 7",
                "Class 8", "Class 9", "Class 10", "Class 11", "Class 12")
        val genders = arrayOf("Male", "Female")
        val sampleData = arrayOf("Sample", "Row", "785245921232", "02/06/1989", "JBP", "Male", "None", "None", "Hindu",
                "B+", "Class 12", "Hindi", "12th")

        val workbook = XSSFWorkbook()

        createListSheet(workbook, classes, 0, "Classes")
        createListSheet(workbook, genders, 1, "Gen")

        val sheet = workbook.createSheet("Sheet 1")
        sheet.protectSheet("abcd")
        var font = workbook.createFont()
        font.bold = true
        font.color = IndexedColors.BLACK.index
        val headerCellStyle = workbook.createCellStyle()
        headerCellStyle.locked = true
        headerCellStyle.setFont(font)
        headerCellStyle.wrapText = true

        val sampleRowStyle = workbook.createCellStyle()
        //sampleRowStyle.fillBackgroundColor = IndexedColors.GREY_50_PERCENT.index
        sampleRowStyle.locked = true
        font = workbook.createFont()
        font.bold = false
        font.color = IndexedColors.BLUE_GREY.index
        sampleRowStyle.setFont(font)
        val headerRow = sheet.createRow(0)
        val sampleRow = sheet.createRow(1)

        addSheetValidation(sheet, 1, 10, "Classes")
        addSheetValidation(sheet, 1, 5, "Gen")

        fillDataInRow(sheet, headerRow, headerCellStyle, columns)
        fillDataInRow(sheet, sampleRow, sampleRowStyle, sampleData)
        sheet.activeCell = "A3";
        workbook.setSheetHidden(0, true)
        workbook.setActiveSheet(1)
        return workbook
    }

    fun fillDataInRow(sheet: Sheet, row: Row, style: XSSFCellStyle, columns: Array<String>) {
        for (index in 0 until columns.size) {
            val cell = row.createCell(index)
            /*if( index == 2 && row.rowNum == 1) {
                style.dataFormat = HSSFDataFormat.getBuiltinFormat("#")
                println(HSSFDataFormat.getBuiltinFormat("#"))
                cell.setCellValue(columns[index].toDouble())
            } else {*/
                cell.setCellValue((columns[index]))
                cell.cellStyle = style
            /*}*/

            cell.cellStyle.locked = true
            sheet.setColumnWidth(index, 3600)
        }
    }

    fun createListSheet(workbook: XSSFWorkbook, items: Array<String>, column: Int, category: String) {

        var sheet = workbook.getSheet("ListSheet")
        if (null == sheet) {
            sheet = workbook.createSheet("ListSheet");
        }

        val namedRange: Name
        val colLetter: String = CellReference.convertNumToColString((column))
        val reference: String

        var r = 0
        items.forEach { entry ->
            var row = sheet.getRow(r)
            if (null == row)
                row = sheet.createRow(r)
            row.createCell(column).setCellValue(entry);
            r++
        }
        val to = items.size - 1
        namedRange = workbook.createName()
        namedRange.nameName = category
        reference = "ListSheet!\$$colLetter\$1:\$$colLetter$to"
        namedRange.refersToFormula = reference;
        sheet.isSelected = false;

    }

    fun addSheetValidation(sheet: Sheet, rowIndex: Int, colIndex:Int, category: String) {
        val dataValidationHelper = sheet.dataValidationHelper
        val constraint = dataValidationHelper.createFormulaListConstraint(category);
        val addressList = CellRangeAddressList(rowIndex, 1000, colIndex, colIndex)
        val validation = dataValidationHelper.createValidation(constraint, addressList)
        sheet.addValidationData(validation)
    }
}
