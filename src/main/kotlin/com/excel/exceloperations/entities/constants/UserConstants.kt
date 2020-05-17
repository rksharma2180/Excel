package com.excel.exceloperations.entities.constants

import java.util.*

class UserConstants {
    val ENTITY_NAME = "users"

    object FieldName {
        val ID = "id"
        val LOGIN = "login"
        val ROLES = "roles"
        val PRINTERS = "printers"
    }

    object JoinInfo {
//        val ROLES = JoinTableInfo.of(User_.roles, ENTITY_NAME, FieldName.ROLES)
//        val PRINTERS = JoinTableInfo.of(User_.printers, ENTITY_NAME, FieldName.PRINTERS)
    }

    object Wrapper {
        val DETAILS = "details"
    }

    val details = initializeDetailsFieldList()

    private fun initializeDetailsFieldList(): List<String> {
        val detailFields = ArrayList<String>()
        detailFields.add(FieldName.ID)
        detailFields.add(FieldName.LOGIN)
        detailFields.add(FieldName.ROLES)
//        detailFields.addAll(GenericFilterUtil.combine(RoleConstants.ENTITY_NAME, RoleConstants.details))
        detailFields.add(FieldName.PRINTERS)
//        detailFields.addAll(GenericFilterUtil.combine(PrinterConstants.ENTITY_NAME, PrinterConstants.details))
        return detailFields
    }
}
