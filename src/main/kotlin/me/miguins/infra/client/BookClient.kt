package me.miguins.infra.client

import io.micronaut.http.annotation.Body
import io.micronaut.nats.annotation.NatsClient
import io.micronaut.nats.annotation.Subject
import me.miguins.entrypoint.model.BookDto

@NatsClient
interface BookClient {

    @Subject("books.create")
    fun sendNewBook(@Body bookDto: BookDto)

    @Subject("books.update")
    fun sendUpdateBook(@Body bookDto: Pair<String, BookDto>)

    @Subject("books.delete")
    fun sendDeleteBook(@Body id: String)
}