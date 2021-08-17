package me.miguins.service

import me.miguins.model.Book
import javax.inject.Singleton

@Singleton
class BookServiceImpl : BookService {

    override fun create(book: Book): Book {
        println("call broker")
        return book
    }
}