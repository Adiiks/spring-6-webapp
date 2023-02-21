package com.adrian.spring6webapp.services;

import com.adrian.spring6webapp.domain.Book;

public interface BookService {

    Iterable<Book> findAll();
}
