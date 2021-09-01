package me.miguins.infra.service

import me.miguins.core.mapper.BookConverter
import me.miguins.core.model.Book
import me.miguins.core.ports.NatsServicePort
import me.miguins.entrypoint.model.BookDto
import me.miguins.infra.client.BookClient
import javax.inject.Singleton

@Singleton
class NatsService(private val bookClient: BookClient): NatsServicePort {

    override fun sendNatsNewBook(book: Book) {
        bookClient.sendNewBook(BookConverter.bookToBookDto(book))
    }

    override fun sendNatsUpdateBook(id: String, book: Book) {
        bookClient.sendUpdateBook(bookDto = Pair(id, BookConverter.bookToBookDto(book)))
    }

    override fun sendNatsDeleteBook(id: String) {
        bookClient.sendDeleteBook(id)
    }
}