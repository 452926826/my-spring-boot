package com.codedocker.springcloud;

import java.util.List;


class Author extends User {
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}