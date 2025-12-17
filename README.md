# springSecurityTask
📌 Overview

This project is a Spring Boot application that provides a complete user registration system with secure password encryption and database persistence. It demonstrates how to build a real-world authentication flow using Spring Data JPA and Spring Security with a custom Security Filter Chain.

The main goal of this project is to follow best practices for backend development and security, especially for handling user credentials.
✨ Features

Register new users

Save users securely in a relational database

Encrypt passwords before storing them

Custom Spring Security filter chain

Clean layered architecture (Controller, Service, Repository)

Uses Spring Data JPA for ORM

Technologies Used

Java

Spring Boot

Spring Data JPA

Spring Security

Hibernate

MySQL (or any JPA-supported database)

Maven

🔐 Security Implementation

Passwords are encrypted using BCryptPasswordEncoder

Plain text passwords are never stored in the database

Custom SecurityFilterChain configuration instead of deprecated WebSecurityConfigurerAdapter

CSRF and authorization rules are explicitly configured
