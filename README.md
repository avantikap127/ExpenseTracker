# ExpenseTracker 
A full-stack Expense Tracker application that allows users to add, view, filter, and delete expenses.
The application is built using React for the frontend and Spring Boot for the backend, with MySQL as the database.

---
# Live Demo
View the project at: https://expense-tracker-wheat-theta.vercel.app/

---
#🚀 Features

• Add a new expense
• View all expenses
• Filter expenses by category
• Delete an expense
• RESTful API using Spring Boot
• MySQL database integration
• Fully deployed backend with cloud database

---

#🛠️ Technologies Used
***Frontend***

React

JavaScript

Axios / Fetch API

HTML & CSS

***Backend***

Spring Boot

Spring Data JPA

REST API

***Database***

MySQL

***Deployment***

Railway (Backend + Database)

Vercel (Frontend)
---

#Project Architecture
     ┌────────────────────────────────────────────┐
     │               User (Browser)                │
     │      ────────────────┬───────────────────   │
     │                       │                     │
     │        Enters expense details via UI        │
     └───────────────────────┼─────────────────────┘
                             │
                             ▼
                ┌──────────────────────────────┐
                │        React Frontend         │
                │ - Displays expense dashboard  │
                │ - Handles form submission     │
                │ - Calls backend APIs          │
                │ - Displays fetched expenses   │
                └──────────────┬────────────────┘
                               │ (Axios / Fetch API)
                               ▼
                ┌──────────────────────────────┐
                │      Spring Boot Backend      │
                │ - REST API Controllers        │
                │ - Business logic (Service)    │
                │ - Handles CRUD operations     │
                │ - Communicates with database  │
                └──────────────┬────────────────┘
                               │ (JPA Repository)
                               ▼
                ┌──────────────────────────────┐
                │        MySQL Database         │
                │ - Stores expenses             │
                │ - Stores categories & types   │
                │ - Maintains relational data   │
                └──────────────┬────────────────┘
                               │ (Query Results)
                               ▼
                ┌──────────────────────────────┐
                │        JSON API Response      │
                │ Example:                      │
                │ [                             │
                │  {                            │
                │   "id": 1,                    │
                │   "amount": 500,              │
                │   "description": "Food",      │
                │   "category": "Dining"        │
                │  }                            │
                │ ]                             │
                └──────────────────────────────┘
---



