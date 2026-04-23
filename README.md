# Smart Campus Sensor & Room Management API

## Overview
This project is a RESTful API built using Java, JAX-RS (Jersey), Maven, and an embedded Grizzly HTTP server. It was developed as part of the Client-Server Architectures coursework.

The API manages three main resources:
- Rooms
- Sensors
- Sensor Readings

The system uses in-memory data structures only (`HashMap` and `ArrayList`) and does not use any database.

Base URL:
http://localhost:8080/api/v1/

---

## API Design

The API follows RESTful principles with resource-based endpoints:

- /rooms → Manage rooms
- /sensors → Manage sensors
- /sensors/{id}/readings → Manage sensor readings

Features:
- Versioned API (/api/v1)
- Sub-resource locator pattern
- Query parameter filtering (?type=)
- Proper HTTP status codes
- Custom exception handling
- Global error handling
- Request and response logging

---

## Technologies Used

- Java
- JAX-RS (Jersey)
- Maven
- Embedded Grizzly HTTP Server

---

## How to Build and Run

1. Clone the repository
git clone https://github.com/YOUR_USERNAME/smart-campus-api.git
cd smart-campus-api

2. Build the project
mvn clean compile

3. Run the server
mvn exec:java

4. Open the API
http://localhost:8080/api/v1/

---

## Sample curl Commands

1. Discovery endpoint
curl http://localhost:8080/api/v1/

2. Create a room
curl -X POST http://localhost:8080/api/v1/rooms -H "Content-Type: application/json" -d "{\"id\":\"LAB-101\",\"name\":\"Computer Lab 101\",\"capacity\":40}"

3. Get all rooms
curl http://localhost:8080/api/v1/rooms

4. Create a sensor
curl -X POST http://localhost:8080/api/v1/sensors -H "Content-Type: application/json" -d "{\"id\":\"TEMP-001\",\"type\":\"Temperature\",\"status\":\"ACTIVE\",\"currentValue\":0,\"roomId\":\"LAB-101\"}"

5. Get sensors (filtered)
curl http://localhost:8080/api/v1/sensors?type=Temperature

6. Add sensor reading
curl -X POST http://localhost:8080/api/v1/sensors/TEMP-001/readings -H "Content-Type: application/json" -d "{\"value\":26.7}"

7. Get sensor readings
curl http://localhost:8080/api/v1/sensors/TEMP-001/readings

---

## Error Handling

The API uses custom exception handling:

- RoomNotEmptyException → 409 Conflict
- LinkedResourceNotFoundException → 422 Unprocessable Entity
- SensorUnavailableException → 403 Forbidden
- GlobalExceptionMapper → 500 Internal Server Error

---

## Logging

A logging filter is implemented using:
- ContainerRequestFilter
- ContainerResponseFilter

It logs:
- Incoming HTTP method and URI
- Outgoing HTTP response status

---

## Report Answers

### Part 1

Default lifecycle of JAX-RS resource classes:
JAX-RS resource classes are typically request-scoped, meaning a new instance is created for each incoming request. This prevents shared request data between clients. Since this project uses in-memory storage, shared data must be stored in static collections such as HashMap and handled carefully to avoid race conditions.

Why hypermedia is important:
Hypermedia allows clients to discover available resources and actions dynamically through API responses. This reduces reliance on static documentation and improves flexibility.

---

### Part 2

Returning IDs vs full objects:
Returning only IDs reduces bandwidth usage but requires additional requests from clients. Returning full objects provides complete data but increases response size.

Is DELETE idempotent:
Yes, DELETE is idempotent because repeating the same request results in the same final state. Once a resource is deleted, further DELETE requests do not change the system.

---

### Part 3

Effect of @Consumes(MediaType.APPLICATION_JSON):
If a client sends a different content type such as text/plain, the request may be rejected with HTTP 415 Unsupported Media Type because JAX-RS cannot process it.

Why use query parameters:
Query parameters are better for filtering collections. /sensors?type=CO2 clearly indicates filtering, while path-based filtering is less flexible.

---

### Part 4

Benefits of sub-resource locator:
This pattern improves modularity by separating nested resource logic into different classes. It makes the code easier to maintain and extend.

---

### Part 5

Why HTTP 422 instead of 404:
HTTP 422 is used because the request is valid, but the data inside it is incorrect. The issue is not the URL, but the invalid reference inside the request body.

Why stack traces should not be exposed:
Stack traces reveal internal implementation details such as class names and file paths, which can be used by attackers. Returning a generic error response improves security.

Why use filters for logging:
Filters allow logging to be handled in one place instead of repeating logging code in every resource method. This improves consistency and maintainability.
