# 📚 Trending Books 2025 App

## 🌐 Live Website

🔗 [Click here to visit the site](https://topbooklist-2.onrender.com/)

A small functional web app that shows **Top 20 Trending Books of 2025**.  

It fetches book data from the Google Books API, saves it to MongoDB, and displays it in a responsive Bootstrap-based frontend.

---

## 🎯 Features

✅ Fetches 20 books using Google Books API  
✅ Stores books in MongoDB Atlas  
✅ Spring Boot REST API backend  
✅ Clean Bootstrap UI with search bar  
✅ "Refresh" button to update books in DB  
✅ Search filter on title and description  

---

## 🛠️ Tech Stack

- Backend: Java 21, Spring Boot  
- Database: MongoDB Atlas  
- Frontend: HTML, CSS, Bootstrap 5, JavaScript  

---

## ⚙️ Setup Instructions

### 1️⃣ Clone this repository

```bash
git clone https://github.com/megha0705/Maketronics-tech-assignment
cd demo
```
2️⃣ Backend Setup (Spring Boot)
Ensure you have Java 21 and Maven installed.

2.1 Configure MongoDB
Create a new free database in MongoDB Atlas (or reuse existing account).

Add your connection string in application.properties:
```properties
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster-url>/<dbname>?retryWrites=true&w=majority
```

2.2 Install Dependencies & Run
```bash
mvn clean install
mvn spring-boot:run
```
✅ Backend will start at: http://localhost:8080

3️⃣ Frontend Setup
No build tools needed!

Open index.html in your browser.

✅ Make sure your backend is running on localhost:8080.

## 🚀 Usage
**Refresh Book Data**

Click the "Refresh" button in the navbar.

It calls the /api/refresh GET endpoint on the backend.

Backend fetches new books from Google Books API and saves them in MongoDB.

**Search**

Use the search bar to filter books by title or description.

## 📦 API Endpoints
GET /api/getAllBook – Fetch all books from DB

GET /api/refresh – Fetch new books from Google Books API and update DB

## 💭 Assumptions Made
Google Books API is freely accessible without an API key for basic subject search.

The database is pre-populated once you hit "Refresh".

For simplicity, when refreshing, the app may overwrite existing data.

App runs locally on localhost:8080 for backend, and static HTML/JS for frontend.

CORS is configured to allow local development (allowedOrigins="*").
## Screenshot of the Website

<img width="1906" height="970" alt="image" src="https://github.com/user-attachments/assets/ec797d2e-19f7-49c1-a7cd-f8af02e4a735" />
