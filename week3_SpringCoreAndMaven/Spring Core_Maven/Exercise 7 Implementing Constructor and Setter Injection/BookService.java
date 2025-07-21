package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private String name;
    private BookRepository bookRepository;

    public BookService(String name) {
        this.name = name;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook() {
        System.out.println(name);
        bookRepository.save();
    }
}