package com.adrian.spring6webapp.services;

import com.adrian.spring6webapp.domain.Author;

public interface AuthorService {

    Iterable<Author> findAll();
}
