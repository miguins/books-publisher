package me.miguins.infra

import io.micronaut.http.annotation.Body
import io.micronaut.nats.annotation.NatsClient
import io.micronaut.nats.annotation.Subject
import me.miguins.model.Book

@NatsClient
interface BookClient {

    @Subject("books.create")
    fun sendNewBook(@Body book: Book)

    @Subject("books.update")
    fun sendUpdateBook(@Body book: Pair<String, Book>)

    @Subject("books.delete")
    fun sendDeleteBook(@Body id: String)
}