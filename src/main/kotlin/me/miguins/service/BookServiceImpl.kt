package me.miguins.service

import me.miguins.infra.BookClient
import me.miguins.model.Book
import javax.inject.Singleton

@Singleton
class BookServiceImpl(private val bookClient: BookClient) : BookService {

    override fun create(book: Book): Book {
        bookClient.sendNewBook(book)
        return book
    }

    override fun update(id: String, book: Book): Book {
        bookClient.sendUpdateBook(book = Pair(id, book))
        return book
    }

    override fun delete(id: String) {
        bookClient.sendDeleteBook(id)
    }
}