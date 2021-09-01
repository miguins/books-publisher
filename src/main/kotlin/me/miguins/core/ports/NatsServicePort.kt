package me.miguins.core.ports

import me.miguins.core.model.Book
import javax.inject.Singleton

@Singleton
interface NatsServicePort {

    fun sendNatsNewBook(book: Book)
    fun sendNatsUpdateBook(id: String, book: Book)
    fun sendNatsDeleteBook(id: String)
}