package com.excel.exceloperations.entities

import javax.persistence.*

//@Data
//@Entity
//@Table(name = "permissions")
class Permission(
        var id: Long? = null,
        var name: String?,
        var roles: Collection<Role> = listOf()
)
