package com.excel.exceloperations.entities.uploads

import com.excel.exceloperations.entities.Student

class ExcelStudentEntityRow(var student: Student? = null, override var rowIndex: Int) : ExcelResponseEntity() {

}