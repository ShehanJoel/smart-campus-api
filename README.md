# Smart Campus Sensor & Room Management API

## Overview
This project is a RESTful API built using Java, JAX-RS (Jersey), Maven, and an embedded Grizzly server. It was developed for the Client-Server Architectures coursework.

The API manages three main resources:
- Rooms
- Sensors
- Sensor Readings

The system uses in-memory data structures only (`HashMap` and `ArrayList`) and does not use any database.

Base URL:
```text
http://localhost:8080/api/v1/
