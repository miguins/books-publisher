package me.miguins.entrypoint.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import me.miguins.core.mapper.BookConverter
import me.miguins.entrypoint.model.BookDto
import me.miguins.core.ports.BookServicePort
import javax.validation.Valid

@Validated
@Controller("/api/v1/books")
class BookController(private val bookServicePort: BookServicePort) {

    @Post
    fun create(@Valid @Body request: BookDto): HttpResponse<Any> {
        return HttpResponse.created(bookServicePort.create(BookConverter.bookDtoToBook(request)))
    }

    @Put("/{id}")
    fun update(@PathVariable id: String, @Valid @Body request: BookDto): HttpResponse<Any> {
        return HttpResponse.ok(bookServicePort.update(id, BookConverter.bookDtoToBook(request)))
    }

    @Delete("/{id}")
    fun delete(@PathVariable id: String): HttpResponse<Any> {
        bookServicePort.delete(id)
        return HttpResponse.ok()
    }
}