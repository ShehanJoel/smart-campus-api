# 🏫 Smart Campus Sensor & Room Management API

A RESTful API built using **Java, JAX-RS (Jersey), Maven, and an embedded Grizzly HTTP server** for managing campus rooms and IoT sensors.  
Developed for the **5COSC022W Client-Server Architectures coursework**.

---

## 📖 Overview

This API simulates a Smart Campus system with three main resources:

- 🏠 Rooms  
- 📡 Sensors  
- 📊 Sensor Readings  

### ✨ Features

- Room management (create, retrieve, delete)  
- Sensor registration and filtering (`?type=`)  
- Sensor readings via sub-resource pattern  
- Prevent deleting rooms with sensors  
- Structured error handling (no stack traces)  
- Request & response logging  
- In-memory storage only (`HashMap`, `ArrayList`)  

---

## 🔗 Base URL

`http://localhost:8080/api/v1/`

---

## 🛠️ Technologies Used

- Java  
- JAX-RS (Jersey)  
- Grizzly HTTP Server  
- Maven  
- Jackson  

---

## 🚀 How to Build and Run

1. Clone the repository  
   `git clone https://github.com/YOUR_USERNAME/smart-campus-api.git`

2. Navigate into the project  
   `cd smart-campus-api`

3. Build the project  
   `mvn clean compile`

4. Run the server  
   `mvn exec:java`

5. Open in browser or Postman  
   `http://localhost:8080/api/v1/`

---

## 🔗 API Endpoints

### Discovery
GET `/api/v1` → API info and resource links  

---

### Rooms (`/api/v1/rooms`)
GET `/rooms` → Get all rooms  
GET `/rooms/{id}` → Get room by ID  
POST `/rooms` → Create room  
DELETE `/rooms/{id}` → Delete room (fails if sensors exist)  

---

### Sensors (`/api/v1/sensors`)
GET `/sensors` → Get all sensors  
GET `/sensors?type=Temperature` → Filter sensors  
POST `/sensors` → Create sensor  

---

### Sensor Readings (`/api/v1/sensors/{id}/readings`)
GET `/readings` → Get all readings  
POST `/readings` → Add reading  

---

## 💻 Sample curl Commands

1. Discovery  
   `curl http://localhost:8080/api/v1/`

2. Create Room  
   `curl -X POST http://localhost:8080/api/v1/rooms -H "Content-Type: application/json" -d "{\"id\":\"LAB-101\",\"name\":\"Computer Lab\",\"capacity\":40}"`

3. Get Rooms  
   `curl http://localhost:8080/api/v1/rooms`

4. Create Sensor  
   `curl -X POST http://localhost:8080/api/v1/sensors -H "Content-Type: application/json" -d "{\"id\":\"TEMP-001\",\"type\":\"Temperature\",\"status\":\"ACTIVE\",\"currentValue\":0,\"roomId\":\"LAB-101\"}"`

5. Filter Sensors  
   `curl http://localhost:8080/api/v1/sensors?type=Temperature`

6. Add Sensor Reading  
   `curl -X POST http://localhost:8080/api/v1/sensors/TEMP-001/readings -H "Content-Type: application/json" -d "{\"value\":26.7}"`

7. Get Sensor Readings  
   `curl http://localhost:8080/api/v1/sensors/TEMP-001/readings`

---

## ⚠️ Error Handling

- 400 → Bad request  
- 403 → Sensor in maintenance  
- 404 → Resource not found  
- 409 → Room has sensors  
- 415 → Unsupported media type  
- 422 → Invalid room reference  
- 500 → Internal server error  

---

## 📝 Report Answers

### Part 1
JAX-RS resources are request-scoped, meaning a new instance is created per request. Shared data must be stored in static collections, which require careful handling.

Hypermedia helps clients discover API functionality dynamically, reducing dependency on static documentation.

---

### Part 2
Returning IDs reduces payload size but requires additional requests. Returning full objects is more convenient but increases response size.

DELETE is idempotent because repeating it results in the same final system state.

---

### Part 3
If a client sends non-JSON data, JAX-RS may return HTTP 415.

Query parameters are better for filtering because they are flexible and optional.

---

### Part 4
The sub-resource locator pattern improves modularity by separating nested resource logic into different classes.

---

### Part 5
HTTP 422 is used because the request format is valid but the data inside is incorrect.

Stack traces should not be exposed because they reveal internal system details.

Filters centralize logging, improving maintainability.
