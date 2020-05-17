package com.excel.exceloperations.services

import com.excel.exceloperations.entities.*
import com.excel.exceloperations.entities.uploads.ExcelResponseEntity
import com.excel.exceloperations.entities.uploads.ExcelStudentEntityRow
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
import java.util.Date
import java.text.SimpleDateFormat

@Service
class ExcelService(private val studentRepo: StudentRepo) {

    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    fun processExcelRows(file: MultipartFile): List<ExcelResponseEntity> {
        val workbook = XSSFWorkbook(file.inputStream)
        val worksheet = workbook.getSheetAt(1)
        val list: MutableList<ExcelResponseEntity> = mutableListOf()


        for (i in 1 until worksheet.physicalNumberOfRows) {

            val excelResponseEntity = ExcelStudentEntityRow(rowIndex = i)
            val row = worksheet.getRow(i)
            val studentName = readData(row, 0, excelResponseEntity)
            val aadharNumber = readData(row, 1, excelResponseEntity)
            val primaryMobile = readData(row, 2, excelResponseEntity)
            val admissionNumber = readData(row, 4, excelResponseEntity)
            val secondaryMobile = readData(row, 3, excelResponseEntity)
            val gender = readData(row, 10, excelResponseEntity)
            val fatherName = readData(row, 5, excelResponseEntity)
            val motherName = readData(row, 6, excelResponseEntity)
            val className = readData(row, 9, excelResponseEntity)
            val houseNumber = readData(row, 12, excelResponseEntity)
            val streetName = readData(row, 13, excelResponseEntity)
            val landMark = readData(row, 14, excelResponseEntity)
            val city = readData(row, 15, excelResponseEntity)
            val state = readData(row, 16, excelResponseEntity)
            val dob = readData(row, 7, excelResponseEntity)
            val joiningDate = readData(row, 8, excelResponseEntity)
            val isNew = readData(row, 11, excelResponseEntity)

            excelResponseEntity.rowIndex = i

            validateCellData("Name", studentName, null, excelResponseEntity)
            validateCellData("Aadhar Number", aadharNumber, 12, excelResponseEntity)
            validateCellData("Primary Mobile", primaryMobile, 10, excelResponseEntity)
            validateCellData("Admission Number", admissionNumber, null, excelResponseEntity)

            val user = User(
                    gender = gender,
                    primaryMobile = primaryMobile,
                    secondaryMobile = secondaryMobile,
                    placeOfBirth = ""
            )

            val father = FamilyMember(
                    firstName = fatherName,
                    lastName = "",
                    relation = "father",
                    userId = 1
            )

            val mother = FamilyMember(
                    firstName = motherName,
                    lastName = "",
                    relation = "mother",
                    userId = 1
            )

            val admissionClass = Class(
                    name = className
            )

            val address = Address(
                    houseNumber = houseNumber,
                    streetName = streetName,
                    landMark = landMark,
                    city = city,
                    state = state,
                    addressType = "Current",
                    isActive = true,
                    zipCode = "560068",
                    userId = 1L
            )

            val student = Student(
                    firstName = row.getCell(0).stringCellValue,
                    lastName = "",
                    aadharNumber = aadharNumber,
                    placeOfBirth = "",
                    admissionNumber = admissionNumber,
                    gender = gender,
                    identificationMark_1 = "",
                    identificationMark_2 = "",
                    dob = simpleDateFormat.parse(dob) as Date?,
                    user = user,
                    joinClassId = 1L,
                    joiningDate = simpleDateFormat.parse(joiningDate) as Date?,
                    isNew = isNew.toBoolean()
            )
            excelResponseEntity.student = student
            if (excelResponseEntity.errors.isNotEmpty()) {
                excelResponseEntity.status = "Error"
            }
            list.add(excelResponseEntity)
        }
        return list
    }

    fun readData(row: Row, cellIndex: Int, excelResponseEntity: ExcelResponseEntity): String {
        var value = ""
        try {
            value = if (row.getCell(cellIndex).cellType == Cell.CELL_TYPE_NUMERIC) {
                row.getCell(cellIndex).numericCellValue.toString()
            } else {
                row.getCell(cellIndex).stringCellValue
            }
        } catch(ex: Exception) {

        }
        return value
    }

    fun validateCellData(fieldName:String, value: String?, length: Int?, excelResponseEntity: ExcelResponseEntity) {

        if(value.isNullOrEmpty()) {
            excelResponseEntity.errors.add("value for $fieldName Not present")
            return
        }

        if(null != length && value.trim().length != length) {
            excelResponseEntity.errors.add("$fieldName should be $length digit number")
        }

    }


    fun generateStudentExcelTemplate(): ByteArrayInputStream {

        val outStream = ByteArrayOutputStream()
        val workbook = generateWorkbookTemplate()
        outStream.use {
            workbook.write(outStream)
        }

        return ByteArrayInputStream(outStream.toByteArray())
    }

    fun getHeaderFields(): Map<String, Boolean> {
        val fieldMap = LinkedHashMap<String, Boolean>()
        fieldMap["Name"] = true
        fieldMap["Aadhar Number"] = true
        fieldMap["Mobile Number"] = true
        fieldMap["Secondary mobile"] = false
        fieldMap["Admission Number"] = true
        fieldMap["Father Name"] = false
        fieldMap["Mother Name"] = false
        fieldMap["Date of Birth"] = false
        fieldMap["Admission Date"] = false
        fieldMap["Class of Admission"] = false
        fieldMap["Gender"] = false
        fieldMap["New"] = false
        fieldMap["Flat No."] = false
        fieldMap["Street Name"] = false
        fieldMap["Landmark"] = false
        fieldMap["City"] = false
        fieldMap["State"] = false
        return fieldMap
    }

    fun generateWorkbookTemplate(): XSSFWorkbook {

        val classes =  arrayOf("Class 1", "Class 2", "Class 3", "Class 4", "Class 5","Class 6","Class 7",
                "Class 8", "Class 9", "Class 10", "Class 11", "Class 12")

        val genders = arrayOf("Male", "Female")
        val student = getSampleStudent()
        val workbook = XSSFWorkbook()

        createListSheet(workbook, classes, 0, "Classes")
        createListSheet(workbook, genders, 1, "Gen")

        val sheet = workbook.createSheet("Sheet 1")
        //sheet.protectSheet("")

        val sampleRowStyle = workbook.createCellStyle()
        sampleRowStyle.locked = true
        sampleRowStyle.fillForegroundColor = IndexedColors.GREY_50_PERCENT.index
        sampleRowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND)

        val font = workbook.createFont()
        font.color = IndexedColors.WHITE.index
        sampleRowStyle.setFont(font)

        val headerRow = sheet.createRow(0)

        addSheetValidation(sheet, 1, 9, "Classes")
        addSheetValidation(sheet, 1, 10, "Gen")

        fillHeaderRow(sheet, headerRow, workbook)
        fillDataInRow(sheet, sampleRowStyle, listOf(student))

        sheet.activeCell = "A3";
        workbook.setSheetHidden(0, true)
        workbook.setActiveSheet(1)
        return workbook
    }

    fun fillHeaderRow(sheet: Sheet, row: Row, workbook: XSSFWorkbook) {
        val fieldMap = getHeaderFields()
        val mandatoryCellFont = workbook.createFont()
        val nonMandatoryCellFont = workbook.createFont()
        val mandatoryCellStyle = workbook.createCellStyle()
        val nonMandatoryCellStyle = workbook.createCellStyle()

        mandatoryCellFont.bold = true
        mandatoryCellFont.color = IndexedColors.RED.index
        mandatoryCellStyle.setFont(mandatoryCellFont)
        mandatoryCellStyle.locked = true

        nonMandatoryCellFont.bold = true
        nonMandatoryCellFont.color = IndexedColors.BLACK.index
        nonMandatoryCellStyle.setFont(nonMandatoryCellFont)
        nonMandatoryCellStyle.locked = true

        var counter = 0;
        fieldMap.forEach { (t, u) ->
            val cell = row.createCell(counter)
            //sheet.setColumnWidth(counter++, 3600)

            if (u) {
                cell.cellStyle = mandatoryCellStyle
            } else {
                cell.cellStyle = nonMandatoryCellStyle
            }
            cell.setCellValue(t)
            sheet.autoSizeColumn(counter++)
        }
    }

    fun fillDataInRow(sheet: Sheet, style: XSSFCellStyle, studentList: List<Student>) {
        var counter = 1
        val keys = getHeaderFields().keys

        studentList.forEach { student ->
            val row = sheet.createRow(counter++)
            val studentMap = student.getStudentFieldMap()
            var cellCounter = 0
            keys.forEach {  key ->
                if (studentMap.containsKey(key)) {
                    val cell = row.createCell(cellCounter++)
                    cell.cellStyle = style
                    cell.setCellValue(studentMap[key])
                }
            }
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
        val to = items.size
        namedRange = workbook.createName()
        namedRange.nameName = category
        reference = "ListSheet!\$$colLetter\$1:\$$colLetter$to"
        println(reference)
        namedRange.refersToFormula = reference;
        sheet.isSelected = false;

    }

    fun addSheetValidation(sheet: Sheet, rowIndex: Int, colIndex:Int, category: String) {
        val dataValidationHelper = sheet.dataValidationHelper
        val constraint = dataValidationHelper.createFormulaListConstraint(category);
        val addressList = CellRangeAddressList(rowIndex, 1000, colIndex, colIndex)
        val validation = dataValidationHelper.createValidation(constraint, addressList)

        validation.errorStyle = DataValidation.ErrorStyle.STOP;
        validation.suppressDropDownArrow = true;
        validation.emptyCellAllowed = false;
        validation.showPromptBox = true;
        validation.showErrorBox = true;
        sheet.addValidationData(validation)
    }

    fun getSampleStudent(): Student {
        val user = User(
                gender = "Male",
                primaryMobile = "8602120757",
                secondaryMobile = "",
                placeOfBirth = ""
        )
        return Student(
                firstName = "Sample Row" ,
                lastName = "Row",
                aadharNumber = "12345678912",
                placeOfBirth = "JBP",
                admissionNumber = "Adm-1234",
                gender = "Male",
                identificationMark_1 = "No Mark",
                identificationMark_2 = "No Mark",
                dob = simpleDateFormat.parse("02/06/1925"),
                user = user,
                joinClassId = 1L,
                joiningDate = simpleDateFormat.parse("02/06/1925"),
                isNew = false
        )
    }
}
