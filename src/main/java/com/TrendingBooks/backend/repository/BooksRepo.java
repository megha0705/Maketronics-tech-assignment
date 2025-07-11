package com.TrendingBooks.backend.repository;

import com.TrendingBooks.backend.model.BooksModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends MongoRepository<BooksModel , String> {
}
