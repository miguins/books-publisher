package me.miguins.infra.service

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import me.miguins.core.model.Book
import me.miguins.entrypoint.model.BookDto
import me.miguins.infra.client.BookClient
import java.math.BigDecimal
import java.util.*

@MicronautTest
internal class NatsServiceTest: AnnotationSpec() {

    val client = mockk<BookClient>()
    val service = NatsService(client)

    private lateinit var bookDto: BookDto
    private lateinit var book: Book

    @BeforeEach
    internal fun setUp() {
        bookDto = BookDto("Harry Potter", "J. K. Rowling", BigDecimal("79.90"))
        book = Book("Harry Potter", "J. K. Rowling", BigDecimal("79.90"))
    }

    @Test
    fun `send message to nats - new book with success`(){
        every { client.sendNewBook(any()) } answers { }
        val result = service.sendNatsNewBook(book)
        result shouldBe Unit
    }

    @Test
    fun `send message to nats - update book with success`() {
        val id = UUID.randomUUID().toString()

        every { client.sendUpdateBook(any()) } answers { }
        val result = service.sendNatsUpdateBook(id, book)
        result shouldBe Unit
    }

    @Test
    fun `send message to nats - delete book with success`() {
        val id = UUID.randomUUID().toString()

        every { client.sendDeleteBook(id) } answers {}
        val result = service.sendNatsDeleteBook(id)
        result shouldBe Unit
    }
}