package com.excel.exceloperations.entities

import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

class Staff(
        var id: Long? = null,
        var aadharNumber: String,
        var panCardNumber: String,
        var maritalStatus: String,
        var marriedOn: Date? = null,
        var dob: Date? = null,
        var user: User? = null,
        var userId: Long,
        var addresses: List<Address>? = emptyList(),
        var joiningDate: Date? = null,
        var isNew: Boolean? = null
) {

    fun getFieldMap(): Map<String, String> {
        val staffMap = mutableMapOf<String, String>()

        staffMap["Name"] = "${user?.firstName} ${user?.lastName}"
        staffMap["Aadhar Number"] = aadharNumber
        staffMap["Mobile Number"] = user?.primaryMobile!!
        staffMap["Secondary mobile"] = ""
        staffMap["Father Name"] = "Father"
        staffMap["Mother Name"] = "Mother"
        staffMap["Date of Birth"] = SimpleDateFormat("dd/MM/yyyy").format(dob)
        staffMap["Joining Date"] = SimpleDateFormat("dd/MM/yyyy").format(joiningDate)
        staffMap["Gender"] = user!!.gender
        staffMap["New"] = isNew.toString()
        staffMap["Flat No."] = "#1456"
        staffMap["Street Name"] = "Streety"
        staffMap["Landmark"] = "Landmark"
        staffMap["City"] = "JBP"
        staffMap["State"] = "MP"
        return staffMap
    }

}