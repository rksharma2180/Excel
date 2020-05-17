//package com.excel.exceloperations.entities.requests
//
//import com.urskool.enums.Status
//import java.io.Serializable
//import java.sql.Date
//import java.util.Calendar
//import javax.validation.constraints.Pattern
//
//class TenantCreateRequest(
//        var id: Long? = null,
//        var name: String,
//        var identifier: String?,
//        var code: String?,
//        var schemaName: String?,
//        var domain: String?,
////        @NotNull(message = "contact number cannot be empty")
//        var contact_number: String,
//        var alt_contact_number: String? = null,
//        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", message = "must be a valid email")
//        var email: String,
//        var address: String,
//        var created_at: Date = Date(Calendar.getInstance().timeInMillis),
//        var updated_at: Date = Date(Calendar.getInstance().timeInMillis),
//        var status: Status = Status.REQUESTED
//) : Serializable
