package me.miguins.service

import me.miguins.model.Book
import javax.inject.Singleton

@Singleton
interface BookService {

    fun create(book: Book): Book
    fun update(id: String, book: Book): Book
    fun delete(id: String)
}