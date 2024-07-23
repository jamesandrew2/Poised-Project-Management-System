# Poised-Project-Management-System
Welcome to the Poised Project Management System! This project is a comprehensive solution designed to manage and track various construction projects for a small structural engineering firm named "Poised." The system is built using Java and MySQL, providing robust functionalities for project management.

# Project Features

# Project Information Management

Store detailed information about each project, including:
Project number, name, type (e.g., House, apartment block, store)
Physical address, ERF number
Total fee and amount paid to date
Project deadline
Contact details for the architect, contractor, and customer
Core Functionalities

Capture New Projects: Input detailed information for new projects. If the project name is not provided, it defaults to the customer's surname (e.g., "House Tyson").
Update Projects: Modify project information as required throughout the project's lifecycle.
Finalize Projects: Mark projects as finalized and record the completion date.
List Incomplete Projects: Display projects that are not yet finalized.
List Overdue Projects: Display projects that have passed their due date.
Search Projects: Find projects by entering the project number or name.
Database Design

MySQL Database: A relational database named PoisePMS was designed to store all relevant data.
Entity-Relationship Diagram (ERD): A visual representation of the database schema, illustrating the relationships between tables.
Java Program with JDBC

Read and Write Data: Interact with the database to read and write project and personnel information.
Add New Projects: Capture and store new project information directly into the database.
Update Existing Projects: Modify details of ongoing projects.
Delete Data: Remove projects and associated personnel data.
Finalize Projects: Mark projects as complete and record the date.
Retrieve Projects: Fetch incomplete projects, overdue projects, or specific projects by number or name.
Project Structure

# Database Setup

MySQL database PoisePMS with multiple tables to store project and personnel information.
SQL scripts and ERD included in the repository.
Java Application

Uses JDBC to connect and interact with the MySQL database.
Includes classes and methods for all CRUD operations.
Error handling and input validation implemented.
Javadoc documentation generated for all classes and methods.
Documentation

Comprehensive Readme file.
ERD and SQL scripts.
Screenshots demonstrating database creation, data insertion, and application usage.
How to Run the Project

Database Setup: Import the provided SQL scripts to set up the PoisePMS database in MySQL.
Java Application: Compile and run the Java program using an IDE or command line. Ensure the JDBC driver is included in the classpath.
Configuration: Update the database connection details in the Java program as per your MySQL setup.
