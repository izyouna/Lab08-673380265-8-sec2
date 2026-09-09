# Lab 08 - Software Design (Section 2)

## 📄 รายงานประจำปฏิบัติการ (Lab Report)
- **ลิงก์รายงาน:** [Google Drive Report](https://drive.google.com/file/d/1_GZPXVlZ3IghyPQpc00PPr8Gh11fTWNZ/view?usp=sharing)

---

## 📌 ภาพรวมโครงการ (Project Overview)
โปรเจกต์นี้เป็นงานปฏิบัติการ **Lab 08** สำหรับวิชา **CP353002 Principles of Software Design (Section 2)** พัฒนาด้วย **Java (Spring Boot 3.3.0)**, **Spring Data JPA**, และ **Thymeleaf** โดยมุ่งเน้นการจัดการความสัมพันธ์ของข้อมูลในฐานข้อมูลเชิงสัมพันธ์ (JPA Relationships) และการประยุกต์ใช้หลักการออกแบบซอฟต์แวร์ (Software Design Principles & Patterns):

### 1. ความสัมพันธ์ระดับ Entity (JPA Relationships)
- **1:1 Relationship (`@OneToOne`):** 
  - ความสัมพันธ์ระหว่าง `Product` และ `ProductDetail`
  - ออกแบบตามหลัก **Single Responsibility Principle (SRP)** โดยแยกรายละเอียดทางเทคนิคของสินค้า (เช่น น้ำหนัก, ขนาด, การรับประกัน, ประเทศที่ผลิต) ออกจากข้อมูลสินค้าหลัก
  - ใช้ `CascadeType.ALL` เพื่อให้การจัดการข้อมูลรายละเอียดเกิดขึ้นพร้อมกับสินค้า
- **1:N Relationship (`@OneToMany` & `@ManyToOne`):** 
  - ความสัมพันธ์ระหว่าง `Product` (1) และ `Review` (Many)
  - รองรับการให้คะแนนรีวิวและความคิดเห็นหลายรายการต่อสินค้า 1 ชิ้น ออกแบบตามหลัก **Open/Closed Principle (OCP)**

### 2. รูปแบบการออกแบบซอฟต์แวร์ (Design Patterns & Principles)
- **Strategy Design Pattern:** ใช้ในการคำนวณส่วนลดสินค้าผ่าน `DiscountContext` และ `DiscountStrategy`:
  - `NoDiscountStrategy`: ราคาปกติ (ไม่มีส่วนลด)
  - `MemberDiscountStrategy`: ส่วนลดสมาชิก 10%
  - `SeasonalSaleStrategy`: ส่วนลดเทศกาล 20%
- **Dependency Inversion Principle (DIP):** ใน Layer ต่างๆ เช่น `ProductService` รับ Repositories ผ่าน Constructor Injection โดยอิงกับ Abstractions (`ProductRepository`, `ProductDetailRepository`, `ReviewRepository`)

---

## 📁 โครงสร้างโปรเจกต์ (Project Structure)
```
Lab08-673380265-8-sec2/
├── demo/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/demo/
│   │   │   │   ├── controller/
│   │   │   │   │   └── ProductController.java
│   │   │   │   ├── model/
│   │   │   │   │   ├── Product.java
│   │   │   │   │   ├── ProductDetail.java
│   │   │   │   └── Review.java
│   │   │   │   ├── repository/
│   │   │   │   │   ├── ProductDetailRepository.java
│   │   │   │   │   ├── ProductRepository.java
│   │   │   │   └── ReviewRepository.java
│   │   │   │   ├── service/
│   │   │   │   │   └── ProductService.java
│   │   │   │   ├── strategy/
│   │   │   │   │   ├── DiscountContext.java
│   │   │   │   │   ├── DiscountStrategy.java
│   │   │   │   │   ├── MemberDiscountStrategy.java
│   │   │   │   │   ├── NoDiscountStrategy.java
│   │   │   │   └── SeasonalSaleStrategy.java
│   │   │   │   └── DemoApplication.java
│   │   │   └── resources/
│   │   │       ├── static/css/style.css
│   │   │       ├── templates/products/
│   │   │       │   ├── add.html
│   │   │       │   ├── delete.html
│   │   │       │   ├── edit.html
│   │   │       │   └── list.html
│   │   │       └── application.properties
│   │   └── test/
│   ├── pom.xml
│   └── mvnw
└── README.md
```

---

## ⚙️ การตั้งค่าฐานข้อมูล (Database Configuration)
โปรเจกต์ใช้งานฐานข้อมูล **PostgreSQL** โดยมีการตั้งค่าใน `demo/src/main/resources/application.properties` ดังนี้:

- **Database URL:** `jdbc:postgresql://localhost:5433/lab8`
- **Username:** `postgres`
- **Password:** `123456789`
- **Hibernate DDL Auto:** `update`
- **Server Port:** `8080`

> ⚠️ *หมายเหตุ: โปรดตรวจสอบว่าเซิร์ฟเวอร์ PostgreSQL กำลังทำงานที่พอร์ต 5433 และมีฐานข้อมูลชื่อ `lab8` ก่อนเริ่มรันแอปพลิเคชัน*

---

## 🚀 วิธีการรันโปรเจกต์ (How to Run)
1. ตรวจสอบว่าเปิด Service PostgreSQL เรียบร้อยแล้ว
2. เข้าไปที่โฟลเดอร์ `demo`:
   ```bash
   cd demo
   ```
3. รันแอปพลิเคชันผ่าน Maven Wrapper:
   - **macOS / Linux:**
     ```bash
     ./mvnw spring-boot:run
     ```
   - **Windows:**
     ```bash
     mvnw.cmd spring-boot:run
     ```
4. เปิดเบราว์เซอร์และเข้าไปที่:
   ```
   http://localhost:8080/products
   ```
