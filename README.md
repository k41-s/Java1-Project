# Java Programming 1 Project

## Overview

This is a Java-based desktop application developed using Swing and connected to a Microsoft SQL Server database. The project demonstrates object-oriented and functional programming principles, working with collections, graphical UI design, and data system integration.

The application enables managing data about multiple connected entities through an interactive GUI, using a layered architecture and MVC principles.

---

## Features

### Data Management
- Stores and retrieves data from **Microsoft SQL Server**
- Uses **stored procedures** for all database operations (CRUD)
- Initialization and cleanup handled through:
  - **DDL script** to create all necessary tables
  - **SQL script** to delete all data
- Supports **RSS XML feed parsing** from a remote URL
  - Downloads and stores data locally (including images)
  - Saves image **relative paths** to the database
- Entity data is managed through a **Repository pattern**

### User Roles & Authentication
- **Login form** for access control
- Supports **User registration**
- Two user roles: **Administrator** and **User**
  - Administrator can:
    - Reset database (delete data + images)
    - Load new data using the RSS parser

### User Interface (Swing)
- Uses **Swing framework** for GUI
- Follows **MVC architecture**
- UI structure:
  - `JFrame` serves as the main container
  - `JTabbedPane` used to organize views (`JPanel` per feature)
  - `JMenu` for navigation
- Supports **CRUD operations** for connected entities
- Displays entity data using **JTable** backed by **AbstractTableModel**
- Includes **Drag-and-Drop** functionality for connecting entities
- **JAXB integration** for exporting entity data to XML
- Icons and images managed with utility classes

### Object-Oriented Programming
- Proper use of:
  - **Encapsulation**, **Abstraction**, **Inheritance**, and **Polymorphism**
  - Interfaces for service abstraction
  - Static utility classes with private constructors
  - `equals()`, `hashCode()`, `toString()`, and `Comparable` where applicable
- Implements **Singleton** and **Repository** design patterns
- Makes use of **Enums** and **Generics**

### Functional Programming
- Demonstrates:
  - `Consumer`, `Predicate`, and `Function` interfaces
  - `Optional` with functional constructs
  - Stream operations: `filter()`, `map()`, `sorted()`, etc.
  - Default interface methods
- Comparison of functional and object-oriented paradigms during defense

### Collections & Streams
- Applies **Collections Framework** with proper abstractions (`List`, `Set`, `Map`)
- Prevents duplication using `Set`
- Explains:
  - `ArrayList` vs. `LinkedList`
  - `HashMap` vs. `TreeMap`
  - `HashSet` vs. `TreeSet`
- Demonstrates advanced stream operations

### UI/UX Enhancements
- Uses **FlatLaf** via Maven for modern UI styling
- Utility classes for:
  - `JOptionPane` dialogs
  - `JFileChooser` file handling
  - Icon/image scaling
- Ensures GUI responsiveness:
  - **Multithreading** for time-consuming tasks (e.g., XML parsing, database loading)
  - Clear separation between **main** and **event dispatch threads**

---

## Setup Instructions

1. Create the SQL Server database.
2. Run the DDL script to generate tables.
3. Run the cleanup script to clear any existing data.
4. Compile and run the application.
5. Login as the predefined administrator (added via script).
6. Use admin panel to load initial data using the RSS parser.

---

## Technologies Used

- Java 17+
- Swing (UI Framework)
- Microsoft SQL Server
- JAXB (for XML)
- Maven (for dependency management)
- FlatLaf (modern look and feel)
- JDBC with CallableStatements

---

## Notes

- Replace placeholder entity names with your actual entities.
- Be ready to demonstrate functional vs. OOP paradigms and GUI responsiveness in your defense.
- Ensure images are handled consistently with database and filesystem synchronization.

