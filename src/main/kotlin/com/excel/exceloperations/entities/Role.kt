package com.excel.exceloperations.entities

import javax.persistence.*

//@Data
//@Entity
//@Table(name = "roles")
class Role(
        var id: Long? = null,
        var name: String? = null,
        val users: Collection<User>? = listOf(),
        var permissions: MutableList<Permission> = mutableListOf()
) {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

//    var name: String? = name

//    @ManyToMany
//    val users: Collection<User>? = listOf()
//
//    @ManyToMany
//    @JoinTable(
//            name = "roles_permissions",
//            joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
//            inverseJoinColumns = [JoinColumn(name = "permission_id", referencedColumnName = "id")]
//    )
//    var permissions: Collection<Permission>? = listOf()
}
