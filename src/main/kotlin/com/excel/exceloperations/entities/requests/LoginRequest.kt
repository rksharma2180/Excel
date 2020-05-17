package com.excel.exceloperations.entities.requests

import com.fasterxml.jackson.annotation.JsonRootName
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@JsonRootName("user")
class LoginRequest {
    @NotNull(message = "username cannot be missing")
    @Size(min = 1, message = "username cannot be empty")
//    @Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", message="must be a valid email")
    val username: String? = null
    @NotNull(message = "password cannot be missing")
    @Size(min = 1, message = "password cannot be empty")
    val password: String? = null
}
