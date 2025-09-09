# 🚀 Product Service

**A modern Spring Boot microservice for managing products & categories — built to impress, easy to extend, and fun to explore.**

---

## ✨ Why this project stands out

* **Dynamic search magic** 🧙: Filter, sort, and paginate products with a flexible query engine.
* **Clean & defensive design** 🛡️: Consistent error messages, neat DTOs, and clear separation of concerns.
* **Microservice-ready** 🌐: Hooks into Auth, Eureka, Redis, and MySQL — but runs standalone with H2 for quick demos.
* **Recruiter-friendly** 💼: Demonstrates modern Spring patterns, thoughtful architecture, and production-grade practices.

---

## ⚡ Quickstart

Clone, build, and run in just a few steps:

```bash
git clone <this-repo>
cd product-service
mvn spring-boot:run
```

Open: [http://localhost:9090](http://localhost:9090)

For a no-hassle demo, switch to in-memory H2 DB by updating `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:productdb
```

---

## 🔍 Core APIs

**Products**

* `POST /products` → Add new product
* `GET /products` → List products
* `GET /products/{id}` → View details
* `PATCH /products/{id}` → Update
* `DELETE /products/{id}` → Remove

**Categories**

* `POST /categories` → Add category
* `GET /categories` → List categories

**Search (the fun part 🎯)**

```
GET /search?q=mouse&sort=PRICE_ASC&filters=[{"field":"price","operator":"LT","value":50}]
```

Dynamic + composable = powerful.

---

## 🛠️ Built With

* **Java 21** + **Spring Boot 3.5**
* JPA, Security (OAuth2 ready)
* Redis, Eureka, Lombok
* MySQL (or H2)

---

## 🐞 Error Handling (Developer Happiness)

* `404` when product/category not found
* `401` for unauthorized access
* Responses include hints on how to fix issues 🙌

---

## 🚧 Future Ideas

* Swagger/OpenAPI playground 🎨
* Redis caching for faster reads ⚡
* Docker + CI/CD pipelines 🤖
* Role-based auth with JWT 🔐

---


Product Service is more than a demo — it’s a showcase of modern backend craftsmanship. Perfect for learning, interviews, or kicking off your next microservice!
