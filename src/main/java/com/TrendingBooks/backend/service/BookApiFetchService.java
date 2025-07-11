package com.TrendingBooks.backend.service;

import com.TrendingBooks.backend.config.RestTemplateConfig;
import com.TrendingBooks.backend.model.BooksModel;
import com.TrendingBooks.backend.repository.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class BookApiFetchService {
    @Autowired
    RestTemplate restTemplate;
@Autowired
    BooksRepo booksRepo;


    private String getRandomSubject() {
        List<String> subjects = Arrays.asList("fiction", "history", "technology", "science", "fantasy");
        return subjects.get(new Random().nextInt(subjects.size()));
    }

    private int getRandomIndex() {
        return new Random().nextInt(100);
    }
    public void refreshData(){
       // String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=subject:"+ getRandomSubject()+ "fiction&maxResults=
        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=subject:" + getRandomSubject() + "&startIndex=" + getRandomIndex() + "&maxResults=20";

        ResponseEntity<Map> response = restTemplate.getForEntity(apiUrl , Map.class);
        if(response.getStatusCode() != HttpStatus.OK || response.getBody() == null){
            System.out.println("failed to fetch data");
            return;
        }
        List<Map<String , Object>> items = (List<Map<String, Object>>) response.getBody().get("items");
        if(items == null || items.isEmpty()){
            System.out.println("item is empty");
            return;
        }
        ArrayList<BooksModel > books = new ArrayList<>();
        for(Map<String , Object> item : items){
            Map<String , Object> volumeinfo = (Map<String,Object>)item.get("volumeInfo");
                    String title = (String)volumeinfo.getOrDefault("title" , "No Title");
            String description = (String)volumeinfo.getOrDefault("description" , "No Description");
            if (description.length() > 100) {
                description = description.substring(0, 100) + "...";
            }
            String imgUrl ="";
            try {
                Map<String , Object> img = (Map<String, Object>) volumeinfo.get("imageLinks");
                if(img != null){
                    imgUrl = (String)img.getOrDefault("thumbnail", "");
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            String link = (String)volumeinfo.getOrDefault("infoLink", "");
            BooksModel bookmodel = new BooksModel();
            bookmodel.setLink(link);
            bookmodel.setImgUrl(imgUrl);
            bookmodel.setDescription(description);
            bookmodel.setTitle(title);
            books.add(bookmodel);
        }
        if(!books.isEmpty()){
          booksRepo.deleteAll();
            booksRepo.saveAll(books);
            System.out.println("books are refreshed");
        }
    }
    }

