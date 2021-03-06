package me.miguins.entrypoint.model

import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
data class BookDto(

    @field:NotBlank
    val title: String,

    @field:NotBlank
    val author: String,

    @field:NotNull
    val price: BigDecimal
)