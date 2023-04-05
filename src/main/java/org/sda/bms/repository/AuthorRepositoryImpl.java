package org.sda.bms.repository;

import org.sda.bms.model.Author;

public class AuthorRepositoryImpl extends BaseRepositoryImpl<Author> implements AuthorRepository<Author>{
    public AuthorRepositoryImpl() {
        super(Author.class);
    }
}
