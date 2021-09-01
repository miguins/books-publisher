package me.miguins.core.ports

import me.miguins.core.model.Book
import me.miguins.entrypoint.model.BookDto
import javax.inject.Singleton

@Singleton
interface BookServicePort {

    fun create(book: Book): BookDto
    fun update(id: String, book: Book): BookDto
    fun delete(id: String)
}