# Anime Browsing Platform (Spring Boot, GraphQL, React)

## Short Application Video
<div>This is a test div. </div>
<video width="560" height="315" controls loop autoplay muted>
  <source src="assets/videos/demo.mp4" type="video/mp4">
  Your browser does not support the video tag.
</video>

## Overview

This project is a full-stack anime browsing platform built on:

* **Backend:** Spring Boot microservices (details below)
* **API:** GraphQL for efficient data fetching
* **Frontend:** React for a dynamic and interactive user interface
* **Orchestration:** Docker Compose for easy local setup

The platform allows users to browse a catalog of anime, view details, and potentially interact with other features such as adding favourites.

## Technologies Used

**Backend (Microservices with Spring Boot):**

* **Gateway Service:** [Spring Boot, Java, GraphQL] - Handles routing and potentially authentication.
* **Product Catalog Service:** [Spring Boot, Java, Spring Data MongoDB] - Manages anime product information.
* **Review Service:** [Spring Boot, Java, Spring Data Cassandra] - Handles anime reviews.
* **GraphQL Implementation:** [Spring for GraphQL]
* **Data Persistence:**
    * **MongoDB:** Holds anime data.
    * **Cassandra:** Holds reviews data.
* **Containerization:** Docker

**Frontend (React):**

* **Framework:** React
* **State Management:** useState



## Getting Started

Follow these steps to set up and run the application locally.

### Prerequisites

* Docker and Docker Compose installed on your system. 
* Basic understanding of Git and command-line operations.

### Steps to Start

1.  **Clone the Repository:**

    ```bash
    git clone <your-repository-url>
    cd anime-browsing-project
    ```

2.  **Start Backend Services with Docker Compose:**

    Navigate to the `backend` directory:

    ```bash
    cd backend
    docker-compose up -d
    ```

    This command will build and start the backend microservices (gateway, product catalog, review) as defined in the `docker-compose.yml` file.

3.  **Start Frontend Application:**

    Navigate to the `frontend/anime-browsing-app` directory:

    ```bash
    cd ../frontend/anime-browsing-app
    npm install  # or yarn install
    npm start    # or yarn start
    ```

    This will install the necessary frontend dependencies and start the React development server. The application will typically be accessible at `http://localhost:3000` in your web browser.

## Database Configuration

The backend services rely on MongoDB and Cassandra. The Docker Compose setup should handle the basic initialization for local development. However, you might need to configure specific aspects depending on your requirements.

### MongoDB Configuration

* The MongoDB service is defined in the `backend/docker-compose.yml` file.
* You might need to:
    * **Initialize Databases and Collections:** Depending on your Spring Boot configuration, the application might automatically create databases and collections. If not, you might need to use a MongoDB client (e.g., MongoDB Compass) to create them manually.
    * **Seed Initial Data:** If you need initial data for testing, you can either implement data seeding within your Spring Boot application or use a MongoDB client to import data.
    * **Connection URI:** The Spring Boot application connects to MongoDB using a URI typically defined in the `application.properties` or `application.yml` files of the relevant service (e.g., `product-catalog-service`). Ensure this URI matches the service name and port defined in your `docker-compose.yml`.

### Cassandra Configuration

* The Cassandra service is also defined in the `backend/docker-compose.yml` file.
* You might need to:
    * **Create Keyspaces and Tables:** Cassandra requires you to define keyspaces (similar to databases) and tables. You can connect to the Cassandra instance using a CQL (Cassandra Query Language) client (e.g., `cqlsh` within the Cassandra container) and execute `CREATE KEYSPACE` and `CREATE TABLE` statements.
    * **Define Schemas in Spring Boot:** Your Spring Boot review service will likely use Spring Data Cassandra to interact with the database. Ensure your entity classes and repositories are correctly configured to map to your Cassandra schema.
    * **Connection Details:** Similar to MongoDB, the Cassandra connection details (contact points, keyspace) are usually configured in the `application.properties` or `application.yml` of the review service.

**Example: Connecting to Cassandra using `cqlsh` (within the Docker container)**

1.  Find the Cassandra container ID: `docker ps`
2.  Enter the container: `docker exec -it <cassandra_container_id> bash`
3.  Connect to CQLSH: `cqlsh <cassandra_service_name> 9042` (replace `<cassandra_service_name>` with the name from your `docker-compose.yml`)
4.  Execute CQL commands (e.g., `CREATE KEYSPACE anime_reviews WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };`, `USE anime_reviews;`, `CREATE TABLE reviews (anime_id UUID PRIMARY KEY, user_id UUID, rating INT, comment TEXT);`)

**Note:** The specific configuration details for MongoDB and Cassandra will depend on how you've implemented them in your Spring Boot microservices. Refer to your service-specific configuration files for the exact connection URIs and settings.

## GraphQL API

The backend exposes a GraphQL API, likely accessible through the Gateway Service. You can explore the schema and execute queries using a GraphQL client (e.g., GraphiQL, Apollo Sandbox) at the configured endpoint (check your Gateway Service configuration).

## Further Development/Potential Features

* User review submission and viewing
* Advanced filtering and sorting

## Author

https://github.com/Akanksha6512

