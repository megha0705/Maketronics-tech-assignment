package com.TrendingBooks.backend.service;

import com.TrendingBooks.backend.model.BooksModel;
import com.TrendingBooks.backend.repository.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
@Autowired
    BooksRepo booksRepo;
    public void addBook(String title, String description, String img, String link) {
        BooksModel book = new BooksModel();
        book.setTitle(title);
        book.setDescription(description);
        book.setLink(link);
        book.setImgUrl(img);
        booksRepo.save(book);
    }
}
