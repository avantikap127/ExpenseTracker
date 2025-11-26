# ExpenseTracker 
A simple and efficient console-based Expense Tracker built using Core Java.
This application allows users to add, view, save, and manage daily expenses using JSON file storage.
It is lightweight, beginner-friendly, and demonstrates clean code architecture using Models + Services.

---

#🚀 Features

➕ Add New Expense (title, amount, date)

📄 View All Expenses

💾 Auto-save to JSON file (expenses.json)

📂 Loads previous expenses on startup

🧱 Uses GSON Library for JSON parsing

🧹 Clean OOP structure:

Expense (Model)

ExpenseService (Business Logic)

FileService (Read/Write JSON)

ExpenseTrackerApp (Main Console App)

---

#🛠️ Technologies Used
| Component              | Description                          |
| ---------------------- | ------------------------------------ |
| **Java (Core Java)**   | Main programming language            |
| **GSON 2.10.1**        | JSON serialization/deserialization   |
| **Powershell Scripts** | For building and running the project |
| **JSON**               | Data persistence                     |

---

#How to Run

**1️⃣ Compile the Project (Manual Method)**
javac -cp ".;lib/gson-2.10.1.jar" src/**/*.java -d out

**2️⃣ Run the Project**
java -cp "out;lib/gson-2.10.1.jar" ExpenseTrackerApp

**▶️ Run Using Provided Scripts (Windows PowerShell)**
Run directly
./run.ps1

Build project
./build.ps1

---

#📘 How It Works
ExpenseTrackerApp.java

Handles menu display

Takes user input

Calls services to perform actions

ExpenseService.java

Manages list of expenses

Add, fetch, manage expense operations

FileService.java

Reads expenses from expenses.json on startup

Saves all expenses after updates

Expense.java

Model class with fields:

title

amount

date

---

#📄 Sample JSON Output
[
  {
    "title": "Groceries",
    "amount": 450.50,
    "date": "2025-11-26"
  }
]

---


🧪 Future Enhancements

📊 Monthly/Yearly summary reports

📈 Expense filtering

🔍 Search expenses

💻 GUI version using JavaFX/Swing

☁️ Cloud-based storage option

