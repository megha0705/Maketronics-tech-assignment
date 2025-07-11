package com.TrendingBooks.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
@Service
public class BooksImg {
    @Autowired
    Cloudinary cloudinary;
    public String uploadImg(MultipartFile imgUrl) throws IOException {
        if (imgUrl == null || imgUrl.isEmpty()) {
            throw new IllegalArgumentException("Profile picture must not be null or empty");
        }

        Map uploadResult = cloudinary.uploader().upload(imgUrl.getBytes(), ObjectUtils.asMap("folder", "book/img"));
        String img = uploadResult.get("url").toString();

        return img;
    }
}
