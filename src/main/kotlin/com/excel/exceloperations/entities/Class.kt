package com.excel.exceloperations.entities

import java.io.Serializable

class Class(
        var id: Long? = -1,
        var name: String = "",
        val academicYear: AcademicYear? = null,
        var academicYearId: Long? = null,
        val sections: MutableList<Section> = mutableListOf()
) : Serializable