package com.excel.exceloperations.services.excel

import com.excel.exceloperations.entities.*
import com.excel.exceloperations.entities.uploads.ExcelResponseEntity
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.LinkedHashMap

@Service
class StudentExcelTemplateService: ExcelTemplate {

    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun processExcelRecords(workbook: XSSFWorkbook): List<ExcelResponseEntity<Any>> {

        val list: MutableList<ExcelResponseEntity<Any>> = mutableListOf()
        val worksheet = workbook.getSheetAt(1)

        for (i in 1 until worksheet.physicalNumberOfRows) {

            val excelResponseEntity = ExcelResponseEntity<Any>(rowIndex = i)
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
                    dob = simpleDateFormat.parse(dob),
                    user = user,
                    joinClassId = 1L,
                    joiningDate = simpleDateFormat.parse(joiningDate) as Date?,
                    isNew = isNew.toBoolean()
            )
            excelResponseEntity.entity = student
            if (excelResponseEntity.errors.isNotEmpty()) {
                excelResponseEntity.status = "Error"
            }
            list.add(excelResponseEntity)
        }
        /*if(list.isEmpty()) {
            val excelStudentEntityRow = ExcelStudentEntityRow()
            excelStudentEntityRow.errors.add("No Records Found")
            excelStudentEntityRow.status = "Error"
            list.add(excelStudentEntityRow)
        }*/
        return list
    }

    override fun generateWorkbookTemplate(): XSSFWorkbook {

        val classes =  arrayOf("Class 1", "Class 2", "Class 3", "Class 4", "Class 5","Class 6","Class 7",
                "Class 8", "Class 9", "Class 10", "Class 11", "Class 12")

        val genders = arrayOf("Male", "Female")
        val student = getSampleStudent()
        val workbook = XSSFWorkbook()

        createListSheet(workbook, classes, 0, "Classes")
        createListSheet(workbook, genders, 1, "Gen")

        val sheet = workbook.createSheet("Sheet 1")

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

        fillHeaderRow(sheet, headerRow, workbook, getStudentHeaderFields())
        fillDataInRow(sheet, sampleRowStyle, listOf(student))

        sheet.activeCell = "A3";
        workbook.setSheetHidden(0, true)
        workbook.setActiveSheet(1)
        return workbook
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

    fun getStudentHeaderFields(): Map<String, Boolean> {
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

    fun fillDataInRow(sheet: Sheet, style: XSSFCellStyle, studentList: List<Student>) {
        var counter = 1
        val keys = getStudentHeaderFields().keys

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
}