package com.excel.exceloperations.entities.requests.transport

import java.io.Serializable
import javax.validation.constraints.NotNull

class VehicleCreateRequest(
        @NotNull(message = "name cannot be empty")
        var name: String,
        var seats: Number,
        var max_allowed_seats: Number,
        var care_taker_name: String,
        var care_taker_phone: String
) : Serializable {
}