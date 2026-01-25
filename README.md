# 🌏 Disaster Preparedness Myanmar

A **Spring Boot REST API** designed to help people **request emergency assistance** and allow **organizations to respond efficiently** during disasters in Myanmar.

---

## 📌 Project Overview

**Disaster Preparedness Myanmar** is a backend system that enables:
- Citizens to request help easily during disasters
- Organizations to manage, track, and respond to help requests
- Secure communication using JWT-based authentication

This project focuses on **scalability, security, and real-world disaster response needs**.

---

## 🚀 Features

- 🔐 JWT-based Authentication & Authorization
- 👥 Role-based access (Admin, Organization, User, etc.)
- 🆘 Request help from registered organizations
- 📍 Location-based request handling
- 🗄️ MySQL database integration
- 🐳 Dockerized for easy deployment
- 📡 RESTful API architecture

---
## This API is avaliable at
http://localhost:8080


---

## 🛠️ Tech Stack

| Technology | Description |
|----------|-------------|
| **Spring Boot** | Backend REST API |
| **MySQL** | Relational database |
| **Spring Security + JWT** | Authentication & authorization |
| **Docker** | Containerization |
| **JPA / Hibernate** | ORM |
| **Maven** | Dependency management |

---

## 📂 Project Type

- **Backend Only**
- **REST API**
- No frontend included (can be consumed by Flutter, Web, or Mobile apps)

---

## 🐳 Run with Docker

### Prerequisites
- Docker
- Docker Compose

### Steps

```bash
# Clone the repository
git clone https://github.com/YeZawHlaing/disaster-preparedness-myanmar.git

# Move into project directory
cd disaster-preparedness-myanmar

# Build and run containers
docker-compose up --build

