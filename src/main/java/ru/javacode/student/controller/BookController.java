package ru.javacode.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.javacode.student.model.Book;
import ru.javacode.student.model.dto.BookDtoNew;
import ru.javacode.student.model.dto.BookDtoUpdate;
import ru.javacode.student.service.BookService;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {


    private final BookService bookService;

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable long bookId) {
        return bookService.getBook(bookId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody BookDtoNew bookDtoNew) {
        return bookService.addBook(bookDtoNew);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(@RequestBody BookDtoUpdate bookDtoUpdate) {
        return bookService.updateBook(bookDtoUpdate);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable long bookId) {
        bookService.deleteBook(bookId);
    }

}
