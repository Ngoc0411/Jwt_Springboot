# Jwt_Springboot

Xác thực, đăng kí user sử dụng jwt

### 1. Tạo các bảng trong mysql:

```sh
-- testAuthDb.roles definition

CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- testAuthDb.users definition

CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- testAuthDb.user_roles definition

CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  KEY `FKhfh9dx7w3ubf1co1vdev94g3f` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
  
 -- Thêm dữ liệu cho bảng roles 
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

  
```

### 2. Test với các API
Đăng kí:
```sh
curl --location --request POST 'http://localhost:8080/api/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "ngocpv",
    "email": "ngoc@01234",
    "password": "123456",
    "role": ["user"]
}'
```
Response:
```sh
{
    "status": "SUCCESS",
    "message": "User registered successfully!",
    "data": null
}
```

Đăng nhập:
```sh
curl --location --request POST 'http://localhost:8080/api/auth/signin' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "ngocpv",
    "password": "123456"
}'
```
Response:
```sh
{
    "status": "SUCCESS",
    "message": null,
    "data": {
        "id": 13,
        "username": "ngocpv",
        "email": "ngoc@01234",
        "roles": [
            "ROLE_USER"
        ],
        "tokenType": "Bearer",
        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZ29jcHYiLCJpYXQiOjE2MzkxMjIxNzUsImV4cCI6MTYzOTIwODU3NX0.gBDQtne98duAhANAcjwIExe_xF-qT07zQGMw2ms-KTxgf-MzPxggi5kWVr391hy4dq-Ua1qGCtkoldGf0lat1A"
    }
}
```