package com.excel.exceloperations.services.excel

import com.excel.exceloperations.entities.uploads.ExcelResponseEntity
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddressList
import org.apache.poi.ss.util.CellReference
import org.apache.poi.xssf.usermodel.XSSFWorkbook

interface ExcelTemplate {

    fun processExcelRecords(workbook: XSSFWorkbook): List<ExcelResponseEntity<Any>>
    fun generateWorkbookTemplate(): XSSFWorkbook

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

    fun fillHeaderRow(sheet: Sheet, row: Row, workbook: XSSFWorkbook, fieldMap: Map<String, Boolean>) {
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

            if (u) {
                cell.cellStyle = mandatoryCellStyle
            } else {
                cell.cellStyle = nonMandatoryCellStyle
            }
            cell.setCellValue(t)
            sheet.autoSizeColumn(counter++)
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
        namedRange.refersToFormula = reference;
        sheet.isSelected = false;
    }

    fun readData(row: Row, cellIndex: Int, excelResponseEntity: ExcelResponseEntity<Any>): String {
        var value = ""
        try {
            value = if (row.getCell(cellIndex).cellType == Cell.CELL_TYPE_NUMERIC) {
                row.getCell(cellIndex).numericCellValue.toString()
            } else {
                row.getCell(cellIndex).stringCellValue
            }
        } catch(ex: Exception) {
            ex.printStackTrace()
        }
        return value
    }

    fun validateCellData(fieldName:String, value: String?, length: Int?, excelResponseEntity: ExcelResponseEntity<Any>) {

        if (value.isNullOrEmpty()) {
            excelResponseEntity.errors.add("value for $fieldName Not present")
            return
        }

        if (null != length && value.trim().length != length) {
            excelResponseEntity.errors.add("$fieldName should be $length digit number")
        }
    }
}