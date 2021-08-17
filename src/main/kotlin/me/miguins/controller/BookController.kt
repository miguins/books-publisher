package me.miguins.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import me.miguins.model.Book
import me.miguins.service.BookService
import javax.validation.Valid

@Validated
@Controller("/api/v1/books")
class BookController(private val bookService: BookService) {

    @Post
    fun create(@Valid @Body request: Book): HttpResponse<Any> {

        return HttpResponse.created(bookService.create(request))
    }
}