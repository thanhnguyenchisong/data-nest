# 📊 DataNest - Market Data Crawler & Analytics Platform

**DataNest** là nền tảng thu thập, chuẩn hóa và cung cấp dữ liệu cho các thị trường ngách như:
- Bất động sản
- Tài chính
- Crypto

Giúp các doanh nghiệp B2B, nhà đầu tư, startup tiếp cận nguồn dữ liệu sạch, cập nhật liên tục qua **API** hoặc **Dashboard**.

---

## 🚀 Tech Stack

- **Java Quarkus 3.x** (RESTEasy Reactive, Panache ORM)
- **PostgreSQL 15+**
- **Keycloak** (OIDC / JWT)
- **MinIO** (S3 Compatible)
- **Jsoup / Playwright** (Web Crawler)
- **Docker / Docker Compose**

---

## 📂 Project Structure
<pre> src/main/java/com/datanest 
├── api # REST API Resource 
├── crawler # Service Crawl (Jsoup, Playwright) 
├── scheduler # Quarkus Scheduled Jobs 
├── service # Business Logic Layer 
├── repository # Panache Repository 
├── domain # Entity / Model (JPA) 
├── security # Keycloak OIDC Integration 
├── config # App Config 
└── utils # Helper Utils </pre>

---

## 🔧 Environment Configuration

### PostgreSQL
```env
POSTGRES_DB=datanest
POSTGRES_USER=datanest
POSTGRES_PASSWORD=datanest
```

### MinIO
```env
MINIO_ACCESS_KEY=minio
MINIO_SECRET_KEY=minio123
MINIO_ENDPOINT=http://localhost:9000
```

### Keycloak (OIDC)
```env
Issuer URL: http://localhost:8180/realms/datanest
Client ID: datanest-api
Secret: secret-datanest
```
---

## 🐳 Docker Compose Setup

```
docker-compose up -d
```

| Service  | URL                   | Credential          |
| -------- | --------------------- | ------------------- |
| Postgres | localhost:5432        | datanest / datanest |
| MinIO    | localhost:9000 / 9001 | minio / minio123    |
| Keycloak | localhost:8180/admin  | admin / admin       |


🔑 Keycloak Setup
1. Realm: datanest
2. Client: datanest-api (Confidential, secret: secret-datanest)
3. Issuer URL: http://localhost:8180/realms/datanest

📡 API Endpoints Sample

| Method | Endpoint         | Description             |
| ------ | ---------------- | ----------------------- |
| GET    | /api/real-estate | Fetch real estate data  |
| POST   | /api/real-estate | Insert real estate data |
| GET    | /api/crypto      | Fetch crypto data       |

All APIs require Bearer Token (OIDC Keycloak)

⚙️ Development Commands
#### Run dev mode
```./mvnw quarkus:dev```
#### Build native binary
```./mvnw package -Pnative```

```./target/datanest-run```
🔥 Scheduler (Crawler Job)
```java
@Scheduled(every = "24h")
public void crawl() {
    crawlerService.crawl();
}
```
📂 S3 Storage (MinIO)
Bucket: datanest

Lưu dữ liệu HTML / JSON thô (raw) để backup / audit

💰 Business Model

| Plan       | Target                   | Price                 |
| ---------- | ------------------------ | --------------------- |
| Free       | 10 query/day, raw CSV    | Free                  |
| Pro        | API / Dashboard Cleaned  | 990k - 2M VND / month |
| Enterprise | Dedicated API, Full Data | Custom Deal           |

📥 Contact / Founder

DataNest

Email: thanhnguyenchisong@gmail.com