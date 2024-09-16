package ru.javacode.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javacode.student.model.Book;
import ru.javacode.student.model.dto.BookDtoNew;
import ru.javacode.student.model.dto.BookDtoUpdate;
import ru.javacode.student.repository.dao.BookDao;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    public Book getBook(long bookId) {
        return bookDao.getBookById(bookId);
    }

    @Override
    public Book addBook(BookDtoNew bookDtoNew) {
        Book book = new Book();
        book.setTitle(bookDtoNew.getTitle());
        book.setAuthor(bookDtoNew.getAuthor());
        book.setPublishingYear(bookDtoNew.getPublishingYear());

        book = bookDao.addBook(book);

        return book;
    }

    @Override
    public Book updateBook(BookDtoUpdate bookDtoUpdate) {
        Book book = new Book();
        book.setId(bookDtoUpdate.getId());
        book.setTitle(bookDtoUpdate.getTitle());
        book.setAuthor(bookDtoUpdate.getAuthor());
        book.setPublishingYear(bookDtoUpdate.getPublishingYear());

        book = bookDao.updateBook(book);

        return book;
    }

    @Override
    public void deleteBook(long bookId) {
        bookDao.deleteBookById(bookId);
    }

}
