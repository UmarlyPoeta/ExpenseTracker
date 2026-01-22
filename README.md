# README created with Chat GPT

# 💸 Expense Tracker (JavaFX)

**Expense Tracker** is a simple desktop application built with Java and JavaFX that allows users to log, view, and analyze their personal expenses.

## 🖥️ Features

* Add expenses with category, amount, date, and note
* View a list of all recorded expenses
* Automatically save data to a CSV file
* Calculate and display the total amount spent
* Intuitive graphical user interface (GUI) using JavaFX

---

## 🚀 How to Run

### ✅ Requirements

* Java 17 or higher
* JavaFX SDK 24.0.1 installed locally
  (The `JAVAFX_LIB` variable in `run.sh` should point to the `lib` directory of your JavaFX SDK)

### 🧪 Steps

1. **Make sure you have JavaFX SDK installed**
   Download from: [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
   Unzip it and update the path in `run.sh`:

   ```bash
   JAVAFX_LIB="/path/to/javafx-sdk-24.0.1/lib"
   ```

2. **Compile and run the application**

   In the root directory of the project, run:

   ```bash
   chmod +x run.sh
   ./run.sh
   ```

---

## 📁 Project Structure

```
├── run.sh                  # Shell script for building and running
├── expenses.csv            # Data file (auto-generated)
├── src/
│   └── tracker/
│       ├── Expense.java           # Represents a single expense
│       ├── ExpenseManager.java    # Manages expense data and CSV file
│       └── ExpenseTrackerApp.java # Main JavaFX application
```

---

## 🧠 Technologies Used

* Java 17+
* JavaFX (controls: `ListView`, `Label`, `TextField`, `DatePicker`, etc.)
* Object-Oriented Programming
* CSV file handling
* GUI built with `VBox`, `GridPane`, `Scene`, `Stage`

---

## 🌍 Notes

* The interface and all messages are currently in Polish
* Data is saved to `expenses.csv` in the same directory as the app
* Invalid input (e.g., letters instead of a number) will trigger an error alert

---


## 📝 License

Educational project — feel free to use, modify, and build upon it.


