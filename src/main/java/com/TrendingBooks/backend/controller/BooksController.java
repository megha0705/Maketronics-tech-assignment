package com.TrendingBooks.backend.controller;

import com.TrendingBooks.backend.model.BooksModel;
import com.TrendingBooks.backend.repository.BooksRepo;
import com.TrendingBooks.backend.service.BookApiFetchService;
import com.TrendingBooks.backend.service.BookService;
import com.TrendingBooks.backend.service.BooksImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")

public class BooksController {
    @Autowired
    BookService bookService;
    @Autowired
    BooksImg booksImg;
    @Autowired
    BooksRepo booksRepo;
    @Autowired
    BookApiFetchService bookApiFetchService;
    @PostMapping("/uploadBooks")
    public String addBooks(@RequestParam String title , @RequestParam String description ,  @RequestParam MultipartFile imgUrl , @RequestParam String link) throws IOException {
        String img = booksImg.uploadImg(imgUrl);
        bookService.addBook(title , description , img , link);
        return "book added successfully";
    }
    @GetMapping("/getAllBook")
    public List<BooksModel> getBooks(){
        return booksRepo.findAll();
    }
    @GetMapping("/refresh")
    public void refreshedBooks(){
        bookApiFetchService.refreshData();

    }
}
