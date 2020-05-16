package com.excel.exceloperations.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.sql.Date
import javax.persistence.*

//@Data
//@Entity
//@Table(name = "users")
class User(
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var username: String? = null,
        var email: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        @JsonIgnore
        var password: String? = null,
        var isActive: Boolean = false,
        var phone: String? = "",
        var roles: MutableList<Role> = mutableListOf(),

        var dob: Date? = null,
        var placeOfBirth: String,
        var gender: String,
        var religionId: Long? = null,
        var bloodGroupId: Long? = null
) : Serializable {

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
//            inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
//    )
//    var roles: Collection<Role> = listOf()

//    companion object {
//        fun setRoles(user: User, roles: Collection<Role>) {
//            user.roles = roles
//        }
//    }
}
