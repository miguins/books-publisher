package me.miguins.core.mapper

import me.miguins.core.model.Book
import me.miguins.entrypoint.model.BookDto

class BookConverter {

    companion object {

        fun bookToBookDto(book: Book) = BookDto(book.title, book.author, book.price)

        fun bookDtoToBook(bookDto: BookDto) = Book(bookDto.title, bookDto.author, bookDto.price)
    }
}