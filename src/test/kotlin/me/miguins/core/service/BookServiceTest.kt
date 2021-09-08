package me.miguins.core.service

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import me.miguins.core.model.Book
import me.miguins.entrypoint.model.BookDto
import me.miguins.infra.service.NatsService

import java.math.BigDecimal
import java.util.*

@MicronautTest
internal class BookServiceTest: AnnotationSpec() {

    private val natsService = mockk<NatsService>()
    private val bookService = BookService(natsService)

    private lateinit var bookDto: BookDto
    private lateinit var book: Book

    @BeforeEach
    internal fun setUp() {
        bookDto = BookDto("Harry Potter", "J. K. Rowling", BigDecimal("79.90"))
        book = Book("Harry Potter", "J. K. Rowling", BigDecimal("79.90"))
    }

    @Test
    fun `success on sending new book to nats service`() {
        every { natsService.sendNatsNewBook(any()) } answers { book }
        val result = bookService.create(book)
        result shouldBe bookDto
    }

    @Test
    fun `success on sending updated book to nats service`() {
        every { natsService.sendNatsUpdateBook(any(), any()) } answers { book }
        val result = bookService.update(UUID.randomUUID().toString(), book)
        result shouldBe bookDto
    }

    @Test
    fun `success on sending book id to delete to nats service`() {
        val id = UUID.randomUUID().toString()

        every { natsService.sendNatsDeleteBook(id) } answers {}
        val result = bookService.delete(id)
        result shouldBe Unit
    }
}