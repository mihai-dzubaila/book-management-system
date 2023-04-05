package org.sda.bms.repository;

import org.sda.bms.model.Book;
import org.sda.bms.repository.BaseRepositoryImpl;
import org.sda.bms.repository.BookRepository;

public class BookRepositoryImpl extends BaseRepositoryImpl<Book> implements BookRepository<Book> {
    public BookRepositoryImpl() {
        super(Book.class);
    }
}
