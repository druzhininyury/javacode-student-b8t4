package ru.javacode.student.repository;

import ru.javacode.student.model.Book;

public interface BookRepository {

    Book getBookById(long bookId);

    Book addBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(long bookId);

}
