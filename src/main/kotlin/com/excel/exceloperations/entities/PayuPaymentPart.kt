package com.excel.exceloperations.entities

// https://github.com/VinayAddank/webservices/blob/882ba250a7bbe9c7112aa6e2dc1a34640731bfb8/rta-api/registration/src/main/java/org/rta/registration/service/impl/PaymentServiceImpl.java
class PayuPaymentPart(
        var name: String,
        var merchantId: String,
        var value: String,
        var commission: String,
        var description: String
)