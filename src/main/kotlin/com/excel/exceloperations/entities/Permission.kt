package com.excel.exceloperations.entities

import com.excel.exceloperations.entities.Role

//@Data
//@Entity
//@Table(name = "permissions")
class Permission(
        var id: Long? = null,
        var name: String?,
        var roles: Collection<Role> = listOf()
)
