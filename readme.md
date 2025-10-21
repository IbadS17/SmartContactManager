
# 🧠 Smart Contact Manager

A full-featured **Contact Management Web Application** built using **Spring Boot**, **Thymeleaf**, and **MySQL**.  
It allows users to securely sign up, log in, and manage their personal contacts with advanced functionalities like search, sorting, pagination, and CSV export.

---

## 🚀 Features

✅ User Authentication (Sign Up / Login)  
✅ Add, Edit, Delete, and View Contacts  
✅ Profile Picture Upload  
✅ Pagination, Sorting, and Search Filters  
✅ Export Contacts to `.csv`  
✅ Dark Mode Support 🌙  
✅ Secure Password Encryption (BCrypt)  
✅ Fully Responsive UI (Thymeleaf + Bootstrap / Tailwind)  

---

## 🛠️ Tech Stack

**Backend:** Spring Boot, Spring MVC, Spring Data JPA, Hibernate  
**Frontend:** Thymeleaf, HTML, CSS, JavaScript  
**Database:** MySQL  
**Security:** Spring Security with BCrypt Password Encoding  
**Tools:** Maven, IntelliJ IDEA / VS Code / Eclipse  

---

## ⚙️ Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/IbadS17/SmartContactManager.git
cd SmartContactManager
````

### 2. Configure Database

Create a new MySQL database:

```sql
CREATE DATABASE smart_contact_manager;
```

Update your database credentials in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smart_contact_manager
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.thymeleaf.cache=false
```

### 3. Build & Run the Application

```bash
mvn spring-boot:run
```

The app will start at:

```
http://localhost:8080
```

---

## 👥 User Roles

| Role  | Description                                       |
| ----- | ------------------------------------------------- |
| USER  | Can add, view, edit, and delete personal contacts |
| ADMIN | (Optional) Manage users and global settings       |

---

## 📂 Project Structure

```
src/
 ├── main/
 │   ├── java/com/smartcontactmanager/
 │   │    ├── controller/
 │   │    ├── entity/
 │   │    ├── repository/
 │   │    ├── service/
 │   │    └── SmartContactManagerApplication.java
 │   ├── resources/
 │   │    ├── static/
 │   │    ├── templates/
 │   │    └── application.properties
 └── test/
```

---

## 📸 Screenshots

> Will Adding it Soon

---

## 📦 Export to CSV

Users can export all contacts into a `.csv` file with a single click for backup or sharing purposes.

---

## 🔒 Security Features

* Passwords are encrypted using **BCrypt**
* Session management with **Spring Security**
* Role-based access control

---

## 🧑‍💻 Author

**Ibaad Shaikh**
🌐 [Portfolio Website](https://ibads17.github.io/Personal_Portfolio/)
💼 [LinkedIn](https://www.linkedin.com/in/mohd-ibaad-shaikh-99849a1a6/)
📧 [Email](ibaadsk.dev@gmail.com)

---

## ⭐ Contribute

If you'd like to improve or extend this project, feel free to fork and submit a pull request.
