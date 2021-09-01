package me.miguins.core.service

import me.miguins.core.mapper.BookConverter
import me.miguins.core.model.Book
import me.miguins.core.ports.BookServicePort
import me.miguins.core.ports.NatsServicePort
import me.miguins.infra.client.BookClient
import me.miguins.entrypoint.model.BookDto
import javax.inject.Singleton

@Singleton
class BookService(private val natsServicePort: NatsServicePort) : BookServicePort {

    override fun create(book: Book): BookDto {
        val bookDto = BookConverter.bookToBookDto(book)
        natsServicePort.sendNatsNewBook(book)
        return bookDto
    }

    override fun update(id: String, book: Book): BookDto {
        val bookDto = BookConverter.bookToBookDto(book)
        natsServicePort.sendNatsUpdateBook(id, book)
        return bookDto
    }

    override fun delete(id: String) {
        natsServicePort.sendNatsDeleteBook(id)
    }
}