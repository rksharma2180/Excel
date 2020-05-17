package com.excel.exceloperations.entities.requests

import java.io.Serializable
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 *
 *      @NotNull – validates that the annotated property value is not null
        @AssertTrue – validates that the annotated property value is true
        @Size – validates that the annotated property value has a size between the attributes min and max; can be applied to String, Collection, Map, and array properties
        @Min – vValidates that the annotated property has a value no smaller than the value attribute
        @Max – validates that the annotated property has a value no larger than the value attribute
        @Email – validates that the annotated property is a valid email address
        @NotEmpty – validates that the property is not null or empty; can be applied to String, Collection, Map or Array values
        @NotBlank – can be applied only to text values and validated that the property is not null or whitespace
        @Positive and @PositiveOrZero – apply to numeric values and validate that they are strictly positive, or positive including 0
        @Negative and @NegativeOrZero – apply to numeric values and validate that they are strictly negative, or negative including 0
        @Past and @PastOrPresent – validate that a date value is in the past or the past including the present; can be applied to date types including those added in Java 8
        @Future and @FutureOrPresent – validates that a date value is in the future, or in the future including the present
 */
class TenantActivateRequest(
        @NotNull(message = "schemaName cannot be blank")
        var schemaName: String,
        @NotNull(message = "tenantId cannot be blank")
        @Min(value = 1, message = "tenantId is invalid")
        var id: Long,
        var domain: String?
) : Serializable
