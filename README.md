# 🔒 File Hider (Java Project)

A **secure file-hiding application** built in Java.  
This project allows users to **hide (encrypt) and unhide (decrypt) files** on their system, with **email-based authentication (OTP)** for added security.

## ✨ Features
- 🔐 **File Hiding & Encryption** – Securely encrypts a file so it becomes unreadable.
- 🔓 **File Unhiding & Decryption** – Restore files back to original state.
- 👤 **User Authentication System**
    - Sign up with email
    - Login with credentials
    - OTP verification via email
- 📧 **Email Integration**
    - Sends OTP using **SMTP (Gmail)**
    - Uses **Java Mail API**
- 🛢 **Database Integration**
    - User credentials stored in **MySQL** using JDBC
- 🛠 **Maven Project** – Dependencies managed using Maven.

---

## 🏗 Tech Stack
- **Language:** Java 17+
- **Build Tool:** Maven
- **Database:** MySQL (via JDBC)
- **Email Service:** Java Mail (SMTP with Gmail)
- **Encryption:** Custom file encryption logic

---

## 📂 Project Structure
```
src/
 ├── main/
 │   ├── java/
 │   │   ├── model/          # Configs (Environment Variables)
 │   │   ├── service/        # Services like SendOTPService, FileEncryptService
 │   │   ├── dao/            # Database access layer
 │   │   ├── db/             # Database Connection
 │   │   ├── model/          # User models, entities
 │   │   ├── views/          # Screen Views
 │   │   └── Main.java    # Entry point
 │   └── resources/
 │       └── application.properties / .env
```

---

## ⚡ Getting Started

### 1️⃣ Clone the repository
```bash
git clone https://github.com/your-username/file-hider-java.git
cd file-hider-java
```

### 2️⃣ Setup MySQL Database
```sql
CREATE DATABASE file_hider;
USE file_hider;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE data (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    path VARCHAR(255) NOT NULL,
    bin_data blob NOT NULL
);
```

### 3️⃣ Configure Environment Variables
Create a `.env` file in the project root:
```
DB_HOST=localhost
DB_PORT=3306
DB_USER=root
DB_PASSWORD=yourpassword

SMTP_HOST=
SMTP_PORT=
SMTP_USER=
SMTP_PASS=
```

### 4️⃣ Build with Maven
```bash
mvn clean install
```

### 5️⃣ Run the Application
```bash
mvn exec:java -Dexec.mainClass="MainApp"
```

---

## 📧 Email Configuration (Java Mail)
This project uses Gmail SMTP.  
Make sure you:
1. Enable **2FA** on Gmail account.
2. Generate an **App Password**.
3. Use it in `.env` as `APP_PASSWORD`.

---

## 🚀 Future Improvements
- GUI support (JavaFX / Swing)
- Stronger encryption algorithms (AES)
- Multi-file hiding support
- Dockerize application

---

## 🤝 Contributing
Contributions, issues, and feature requests are welcome!  
Feel free to fork this repo and create pull requests.

---

## 📜 License
This project is licensed under the **MIT License**.

---

## 👨‍💻 Author
- **Shani Sharma** – [GitHub](https://github.com/your-username)  