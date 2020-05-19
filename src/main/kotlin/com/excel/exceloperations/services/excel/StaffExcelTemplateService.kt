package com.excel.exceloperations.services.excel

import com.excel.exceloperations.entities.Address
import com.excel.exceloperations.entities.FamilyMember
import com.excel.exceloperations.entities.Staff
import com.excel.exceloperations.entities.User
import com.excel.exceloperations.entities.uploads.ExcelResponseEntity
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat

@Service
class StaffExcelTemplateService: ExcelTemplate {

    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun generateWorkbookTemplate(): XSSFWorkbook {

        val genders = arrayOf("Male", "Female")
        val staff = getSampleStaff()
        val workbook = XSSFWorkbook()

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

        addSheetValidation(sheet, 1, 8, "Gen")

        fillHeaderRow(sheet, headerRow, workbook, getStudentHeaderFields())
        fillDataInRow(sheet, sampleRowStyle, listOf(staff))

        sheet.activeCell = "A3";
        workbook.setSheetHidden(0, true)
        workbook.setActiveSheet(1)
        return workbook
    }

    override fun processExcelRecords(workbook: XSSFWorkbook): List<ExcelResponseEntity<Any>> {

        val list: MutableList<ExcelResponseEntity<Any>> = mutableListOf()
        val worksheet = workbook.getSheetAt(1)

        for (i in 1 until worksheet.physicalNumberOfRows) {

            val excelResponseEntity = ExcelResponseEntity<Any>(rowIndex = i)
            val row = worksheet.getRow(i)

            val staffName = readData(row, 0, excelResponseEntity)
            val aadharNumber = readData(row, 1, excelResponseEntity)
            val primaryMobile = readData(row, 2, excelResponseEntity)
            val secondaryMobile = readData(row, 3, excelResponseEntity)
            val gender = readData(row, 8, excelResponseEntity)
            val fatherName = readData(row, 4, excelResponseEntity)
            val motherName = readData(row, 5, excelResponseEntity)
            val houseNumber = readData(row, 10, excelResponseEntity)
            val streetName = readData(row, 11, excelResponseEntity)
            val landMark = readData(row, 12, excelResponseEntity)
            val city = readData(row, 13, excelResponseEntity)
            val state = readData(row, 14, excelResponseEntity)
            val dob = readData(row, 6, excelResponseEntity)
            val joiningDate = readData(row, 7, excelResponseEntity)
            val isNew = readData(row, 9, excelResponseEntity)

            excelResponseEntity.rowIndex = i

            validateCellData("Name", staffName, null, excelResponseEntity)
            validateCellData("Aadhar Number", aadharNumber, 12, excelResponseEntity)
            validateCellData("Primary Mobile", primaryMobile, 10, excelResponseEntity)

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
            val staff = Staff(
                    aadharNumber = aadharNumber,
                    dob = simpleDateFormat.parse(dob),
                    maritalStatus = "Unmarried",
                    userId = 1L,
                    panCardNumber = "CFCPS97448",
                    joiningDate = simpleDateFormat.parse(joiningDate),
                    isNew = isNew.toBoolean(),
                    user = user
            )
            excelResponseEntity.entity = staff
            if (excelResponseEntity.errors.isNotEmpty()) {
                excelResponseEntity.status = "Error"
            }
            list.add(excelResponseEntity)
        }
        return list
    }

    fun getStudentHeaderFields(): Map<String, Boolean> {
        val fieldMap = LinkedHashMap<String, Boolean>()
        fieldMap["Name"] = true
        fieldMap["Aadhar Number"] = true
        fieldMap["Mobile Number"] = true
        fieldMap["Secondary mobile"] = false
        fieldMap["Father Name"] = false
        fieldMap["Mother Name"] = false
        fieldMap["Date of Birth"] = false
        fieldMap["Joining Date"] = false
        fieldMap["Gender"] = false
        fieldMap["New"] = false
        fieldMap["Flat No."] = false
        fieldMap["Street Name"] = false
        fieldMap["Landmark"] = false
        fieldMap["City"] = false
        fieldMap["State"] = false
        return fieldMap
    }

    fun fillDataInRow(sheet: Sheet, style: XSSFCellStyle, staffList: List<Staff>) {
        var counter = 1
        val keys = getStudentHeaderFields().keys

        staffList.forEach { staff ->
            val row = sheet.createRow(counter++)
            val staffMap = staff.getFieldMap()
            var cellCounter = 0
            keys.forEach {  key ->
                if (staffMap.containsKey(key)) {
                    val cell = row.createCell(cellCounter++)
                    cell.cellStyle = style
                    cell.setCellValue(staffMap[key])
                }
            }
        }
    }

    fun getSampleStaff(): Staff {
        val user = User(
                firstName = "Sample",
                lastName = "Row",
                gender = "Male",
                primaryMobile = "8602120757",
                secondaryMobile = "",
                placeOfBirth = ""
        )

        return Staff(
                aadharNumber = "12345678912",
                dob = simpleDateFormat.parse("02/06/1925"),
                maritalStatus = "Unmarried",
                userId = 1L,
                panCardNumber = "CFCPS97448",
                joiningDate = simpleDateFormat.parse("02/06/1925"),
                isNew = false,
                user = user
        )
    }
}