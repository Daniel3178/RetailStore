# RetailStore

## Description

RetailStore is a **console-based application** developed as the final project for the **IV1350 (Object-Oriented Design)** course at KTH.  
The project applies best practices in designing and implementing an object-oriented retail system, demonstrating principles such as:

- **Low coupling**
- **High cohesion**
- **Encapsulation**
- **Fault tolerance**
- **Unit testing**
- **Layered architecture**

In the final iteration, several key **design patterns** have been incorporated, including **Observer**, **Singleton**, and **Composition**.

> **Note:** The current version uses a hard-coded example to demonstrate the program’s flow.  
> The system provides a solid foundation for future expansion — for example, richer terminal interactivity or more advanced features.

The **domain model** for this project is illustrated below:

<img src="./src/main/resources/diagrams/domain_model/domain_model_v1.8.png" width="900">

## Additional Diagrams

All other design diagrams are available in:  
`./src/main/resources/diagrams`

---

## Setup

### 1. Install Maven

- Follow the official installation guide:  
  https://maven.apache.org/install.html
- **Windows users:** Ensure the Maven `bin` folder is added to your **PATH** environment variable.

### 2. Run the program

```bash
mvnd exec:java
```

## Alternative: Running with Docker

> **Note:** The system does not currently use the database at runtime.  
> A basic database schema exists, but the `Inventory` class still reads from a local text file.  
> Future improvements may include switching to SQL queries for full database integration.

### 1. Install Docker

Follow the official installation guide:  
https://docs.docker.com/get-docker/

### 2. Start the containers

```bash
docker-compose up
```

Stop the containers

```bash
docker-compose down
```

Developed by: Daniel Ibrahimi
Course: IV1350 - Object Oriented Design, KTH - Stockholm

Date: 30-05/24
