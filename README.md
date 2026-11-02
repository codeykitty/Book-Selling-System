[README.md](https://github.com/user-attachments/files/34259394/README.md)
# Book Hub -- Book Selling System

A desktop-based book selling system developed using **Java Swing** and
**MySQL**. The project includes separate user and administrator
interfaces for book-related activities.

## Overview

Book Hub is intended to support the management and purchase of new and
used books through a Java desktop application. The source project
includes screens for user registration and login, administrator login,
book entry, book purchasing, address capture, requests, and
order/booking display.

## Technology Stack

-   **Programming language:** Java
-   **Desktop UI:** Java Swing
-   **Database:** MySQL
-   **Database connectivity:** JDBC
-   **IDE project metadata:** IntelliJ IDEA

## Features

### User

-   User registration and login
-   User dashboard/navigation
-   Buy Books screen
-   Recycle Books entry point
-   History entry point
-   Request screen
-   Address information form

### Administrator

-   Separate administrator login
-   Administrator dashboard
-   Add new books
-   Add old/used books
-   Show orders/bookings
-   Manage users
-   Manage orders
-   Manage administrators

### Book information

The book-entry screens include fields for book name, writer, condition,
publication date, subject, price, publisher, and source.

> **Note:** The repository source contains the screens and navigation
> listed above. Some end-to-end workflows and database operations may
> require configuration or further implementation. Confirm the behaviour
> against the running application.

## Project Structure

The project is organized around Java source files such as:

``` text
Book Selling System Java-Using Swing-Mysql/
├── Main_screen.java
├── Book_selling_system.java
├── Create_ac.java
├── Userlogin.java
├── AdminLogin.java
├── UserHost.java
├── AdminHost.java
├── Add_new_books.java
├── Add_old_books.java
├── BuyBooks.java
├── Address.java
├── Request.java
├── Show_bookings.java
└── User interface and project configuration files
```

The exact project layout may vary depending on the checked-out revision.

## Prerequisites

Before running the application, install and configure:

1.  A compatible **JDK**.
2.  **MySQL Server**.
3.  A compatible **MySQL Connector/J** JDBC driver.
4.  IntelliJ IDEA or another Java IDE (optional, but IntelliJ project
    metadata is included).

## Setup and Run

### 1. Clone the repository

``` bash
git clone https://github.com/codeykitty/Book-Selling-System.git
cd Book-Selling-System
```

If the repository is owned by a different GitHub account, replace the
URL with that repository's clone URL.

### 2. Configure MySQL

-   Start the MySQL server.
-   Create the database required by the application.
-   Create/import the required tables.
-   Update the application's JDBC connection configuration to match your
    local MySQL host, port, database name, username, and password.

**Database setup note:** A verified SQL schema or database export was
not included with the supplied project archive. The database name, table
definitions, and import script must be obtained from the project owner
or created to match the SQL queries in the source code.

### 3. Configure JDBC

Make sure MySQL Connector/J is available on the project's classpath. In
IntelliJ IDEA, add the connector JAR as a project library or dependency.

Do not commit real database passwords, API keys, or other secrets to
GitHub. Use local configuration or environment variables for
credentials.

### 4. Open and run

-   Open the project folder in IntelliJ IDEA.
-   Confirm the JDK and MySQL Connector/J configuration.
-   Build the project.
-   Run the application's main class: `Book_selling_system.java` (or the
    configured main class in the project).

If your IDE identifies a different main class, use the class containing
the application's `main` method.

## Collaborators

This project is maintained in the GitHub repository:

[Book-Selling-System](https://github.com/codeykitty/Book-Selling-System)

To contribute:

1.  Ask the repository owner to invite your GitHub account as a
    collaborator.
2.  Accept the invitation.
3.  Clone the repository.
4.  Create a branch for your changes.
5.  Commit and push your work.
6.  Open a pull request for review, if the team uses pull requests.

Example:

``` bash
git checkout -b feature/your-change
git add .
git commit -m "Describe your change"
git push -u origin feature/your-change
```

## Recommended Git Practices

-   Commit Java source code and required project resources.
-   Do not commit generated build output such as `out/` or compiled
    `.class` files.
-   Do not commit IDE-specific personal workspace files.
-   Keep database credentials out of version control.
-   Add a database export or setup script (for example,
    `database/schema.sql`) when the schema is finalized.

## Limitations and Notes

-   A SQL database export was not present in the supplied archive;
    database setup needs to be documented separately.
-   Payment gateway, shipping-provider, email notification, and
    deployment integrations are not confirmed by the supplied source.
-   Some navigation items may require additional implementation or
    verification before production use.
