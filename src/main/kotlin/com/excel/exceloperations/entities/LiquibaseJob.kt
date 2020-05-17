package com.excel.exceloperations.entities

data class LiquibaseJob(
        val schemaName: String,
        val tenantId: Long?
)
