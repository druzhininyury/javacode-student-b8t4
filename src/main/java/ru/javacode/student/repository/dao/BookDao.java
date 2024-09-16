package ru.javacode.student.repository.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.javacode.student.exception.EntityNotFoundException;
import ru.javacode.student.model.Book;
import ru.javacode.student.repository.BookRepository;

import java.sql.PreparedStatement;

@Component
@RequiredArgsConstructor
public class BookDao implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Book getBookById(long bookId) {
        String sqlQuery = "SELECT * FROM books WHERE id = ?";
        SqlRowSet booksRows = jdbcTemplate.queryForRowSet(sqlQuery, bookId);
        if (booksRows.next()) {
            Book book = new Book();
            book.setId(booksRows.getLong("id"));
            book.setTitle(booksRows.getString("title"));
            book.setAuthor(booksRows.getString("author"));
            book.setPublishingYear(booksRows.getInt("publishing_year"));
            return book;
        } else {
            throw new EntityNotFoundException("Book with id=" + bookId + " hasn't been found.");
        }
    }

    @Override
    public Book addBook(Book book) {
        String sqlQuery = """
                INSERT INTO books (title, author, publishing_year) 
                VALUES (?, ?, ?);""";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, new String[] {"id"});
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getPublishingYear());
            return preparedStatement;
        }, keyHolder);

        book.setId(keyHolder.getKey().longValue());
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        Book dbBook = getBookById(book.getId());
        if (book.getTitle() != null) {
            dbBook.setTitle(book.getTitle());
        }
        if (book.getAuthor() != null) {
            dbBook.setAuthor(book.getAuthor());
        }
        if (book.getPublishingYear() != null) {
            dbBook.setPublishingYear(book.getPublishingYear());
        }

        String sqlQuery = "UPDATE books SET title = ?, author = ?, publishing_year = ? WHERE id = ?;";
        jdbcTemplate.update(sqlQuery, dbBook.getTitle(), dbBook.getAuthor(), dbBook.getPublishingYear(), dbBook.getId());
        return dbBook;
    }

    @Override
    public void deleteBookById(long bookId) {
        throwExceptionIfBookNotExists(bookId);
        String sqlQuery = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sqlQuery, bookId);
    }

    public void throwExceptionIfBookNotExists(long bookId) {
        String sqlQuery = "SELECT COUNT(id) AS result FROM books WHERE id = ?;";
        SqlRowSet responseRows = jdbcTemplate.queryForRowSet(sqlQuery, bookId);
        responseRows.next();
        if (responseRows.getInt("result") == 0) {
            throw new EntityNotFoundException("Book with id=" + bookId + " hasn't been found.");
        }
    }
}
