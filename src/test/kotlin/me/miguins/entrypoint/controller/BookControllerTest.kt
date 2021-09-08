package me.miguins.entrypoint.controller

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import me.miguins.core.ports.BookServicePort
import me.miguins.entrypoint.model.BookDto
import java.math.BigDecimal
import java.util.*

@MicronautTest
class BookControllerTest : AnnotationSpec() {

    val service = mockk<BookServicePort>()
    val target = BookController(service)

    private lateinit var bookDto: BookDto

    @BeforeEach
    fun setUp() {
        bookDto = BookDto("Harry Potter", "J. K. Rowling", BigDecimal("79.90"))
    }

    @Test
    fun `create book endpoint success`() {
        every { service.create(any()) } answers { bookDto }

        val result = target.create(bookDto)

        result.status() shouldBe HttpStatus.CREATED
        result.body() shouldBe bookDto
    }

    @Test
    fun `update book endpoint success`() {
        val id = UUID.randomUUID().toString()

        every { service.update(id, any()) } answers { bookDto }

        val result = target.update(id, bookDto)

        result.status() shouldBe HttpStatus.OK
        result.body() shouldBe bookDto
    }

    @Test
    fun `delete book endpoint success`() {
        val id = UUID.randomUUID().toString()

        every { service.delete(id) } answers { }

        val result = target.delete(id)

        result.status() shouldBe HttpStatus.OK
        result.body() shouldBe null
    }
}
