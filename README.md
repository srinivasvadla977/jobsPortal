# 🧑‍💼 Job Portal Backend - Spring Boot Project

A full-featured Job Portal backend built with **Spring Boot**, **JWT Authentication**, and **MySQL**, containerized with **Docker**, and deployed on **Render**. This project is designed for learning, and production-like experience.

---

## 🔥 Features

- 🧾 User registration & login (JWT-based auth)
- 👥 Role-based access (USER / RECRUITER)
- 📄 Job creation & listing
- 🔍 Search jobs by skill
- 📩 Apply for jobs
- 🔐 Secure endpoints with JWT
- 🌐 Swagger for API testing
- 🐳 Dockerized and cloud-deployed

---

## 🛠 Tech Stack

| Layer | Tech |
|------|------|
| Language | Java 21 |
| Framework | Spring Boot |
| Security | Spring Security + JWT |
| Database | MySQL (Remote: on Metrics) |
| API Docs | Swagger / OpenAPI |
| Build Tool | Maven |
| Container | Docker |
| Hosting | Render |

---

## 🚀 Deployment (Render)

The app is deployed live using Render's free tier.

**Live URL with Swagger UI:** [https://jobsportal-1-rocy.onrender.com/swagger-ui/index.html)  

---

## 🧪 API Testing (via Swagger)

- Login: `/api/auth/login`
- Register: `/api/users/register`
- Create Job: `/api/jobs`
- Apply to Job: `/api/applications/apply`
- View Applications: `/api/applications/job/{jobId}`

🔐 For protected endpoints, use the JWT token in the `Authorize 🔒` section of Swagger.

---

## 🐳 Docker

To run locally using Docker:

```bash
docker build -t job-portal .
docker run -p 8080:8080 job-portal
