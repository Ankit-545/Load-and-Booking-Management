# Load & Booking Management System

## 📌 Project Overview
This backend system, built using **Spring Boot** and **PostgreSQL**, efficiently manages **Load & Booking operations**. 

## 🚀 Features
- **Load Management**: Create, update, retrieve, and delete load records.
- **Booking Management**: Handle bookings with status updates.
- **Validation & Error Handling**: Ensures data integrity and robustness.

---
## 🏗️ Tech Stack
- **Backend**: Spring Boot, Lombok, Jakarta Validation
- **Database**: PostgreSQL

---
## 📦 API Specifications

### Load Entity
```json
{
  "id": "UUID",
  "shipperId": "String",
  "facility": {
    "loadingPoint": "String",
    "unloadingPoint": "String",
    "loadingDate": "Timestamp",
    "unloadingDate": "Timestamp"
  },
  "productType": "String",
  "truckType": "String",
  "noOfTrucks": "int",
  "weight": "double",
  "comment": "String",
  "datePosted": "Timestamp",
  "status": "POSTED | BOOKED | CANCELLED"
}
```
✅ **Rules**:
- Status defaults to `POSTED` on creation.
- Changes to `BOOKED` when a booking is made.
- Changes to `CANCELLED` when a booking is deleted.

### Booking Entity
```json
{
  "id": "UUID",
  "loadId": "UUID",
  "transporterId": "String",
  "proposedRate": "double",
  "comment": "String",
  "status": "PENDING | ACCEPTED | REJECTED",
  "requestedAt": "Timestamp"
}
```
✅ **Rules**:
- Cannot create a booking if load status is `CANCELLED`.
- Status updates to `ACCEPTED` when confirmed.

---
## 🔹 API Endpoints

### **Load Management**
- **POST** `/load` → Create a new load
- **GET** `/load` → Fetch loads (filter by `shipperId`, `truckType`, etc.)
- **GET** `/load/{loadId}` → Get load details
- **PUT** `/load/{loadId}` → Update load details
- **DELETE** `/load/{loadId}` → Delete a load

### **Booking Management**
- **POST** `/booking` → Create a new booking
- **GET** `/booking` → Fetch bookings (filter by `shipperId`, `transporterId`)
- **GET** `/booking/{bookingId}` → Get booking details
- **PUT** `/booking/{bookingId}` → Update booking details
- **DELETE** `/booking/{bookingId}` → Delete a booking

---
## ⚙️ Setup Instructions
### Prerequisites
- Install **Java 20**
- Install **PostgreSQL**
- Install **Maven**

### Steps to Run
1. **Clone the Repository**
   ```sh
   git https://github.com/Ankit-545/Load-and-Booking-Management.git
   cd Load-and-Booking-Management
   ```
2. **Configure Database**
   - Update `application.properties` with your PostgreSQL credentials.
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_dbname      
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
3. **Build & Run the Application**
 

---
## 📜 Assumptions
- The **shipperId** and **transporterId** are valid identifiers.
- Load status is **automatically managed** based on booking actions.

