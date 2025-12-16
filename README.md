📝 Blog Platform – Backend (Spring Boot)

📌 Project Description

This project is a RESTful Blog Platform backend developed using Java and Spring Boot.
It provides secure and scalable APIs for managing users, blog posts, categories, and comments, following industry-standard backend development practices.

The application implements JWT-based authentication and authorization, follows a layered architecture (Controller–Service–Repository), and persists data using Spring Data JPA and Hibernate with a relational database.

The project also supports pagination, sorting, searching, and media handling, making it suitable for real-world backend and microservices-based systems.

⚙️ Features / Functionalities

👤 User Management
- User registration and login  
- Secure authentication using JWT  
- Role-based access control (Admin / User)  
- Protected REST endpoints using Spring Security  

📝 Blog Post Management
- Create, update, delete, and view blog posts  
- Fetch all blog posts with pagination and sorting  
- Retrieve posts by user or category  
- Search blog posts by title keyword  
- Upload and serve blog post images  
- Clean REST API design following HTTP standards  

💬 Comment Management
- Add comments to blog posts  
- View comments associated with a specific post  
- Proper entity relationships using JPA & Hibernate  

🔍 Pagination, Sorting & Searching
- Server-side pagination for optimized performance  
- Dynamic sorting (ascending / descending) by fields  
- Keyword-based searching for blog posts  
- Customizable page size and page number via query parameters  

🔐 Security
- JWT token generation and validation  
- Secured APIs using authentication filters  
- Password encryption and secure access handling  

🗄️ Database Handling
- Relational database integration using Spring Data JPA  
- Entity mapping and relationships using Hibernate  
- Optimized query handling and repository abstraction  

⚠️ Exception Handling & Validation
- Global exception handling  
- Request validation using Spring Validation  
- Consistent and meaningful API error responses  

🧰 Tech Stack Used

🔹 Backend
- Java  
- Spring Boot  
- Spring MVC  
- Spring Security  
- Spring Data JPA  
- Hibernate  

🔹 Database
- PostgreSQL  

🔹 Authentication
- JWT (JSON Web Token)  

🔹 Tools & Build
- Maven  
- Postman  
- Git  

🚀 Key Highlights
- RESTful API design  
- Secure authentication with JWT  
- Pagination, sorting, and search support  
- Image upload and download functionality  
