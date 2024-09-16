package ru.javacode.student.service;

import ru.javacode.student.model.Book;
import ru.javacode.student.model.dto.BookDtoNew;
import ru.javacode.student.model.dto.BookDtoUpdate;

public interface BookService {

    Book getBook(long bookId);

    Book addBook(BookDtoNew bookDtoNew);

    Book updateBook(BookDtoUpdate bookDtoUpdate);

    void deleteBook(long bookId);

}
