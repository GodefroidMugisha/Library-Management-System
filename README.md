Here’s the updated **README.md** file without the license, screenshots, and clone instructions:

---

# Library Management System

This project is a simple **Library Management System** written in **Java**. It demonstrates the use of Object-Oriented Programming (OOP) principles by modeling a library where members can borrow and return books. The system manages books, members, and loans using Java classes and basic functionality to simulate the operations of a library.

## Features

- **Book Management**: 
  - Add books to the library collection.
  - Remove books from the library.
  - Check the availability of books.
  
- **Member Management**: 
  - Create library members.
  - Allow members to borrow and return books.
  - Keep track of books borrowed by each member.

- **Loan Management**:
  - Create loans when members borrow books.
  - Automatically track due dates (14-day loan period).
  - Return books and close loans.

## Classes

1. **Book**: Represents a book in the library. Each book has an ID, title, author, and availability status.
   - Methods: `getBookInfo()`, `setAvailability()`, `isAvailable()`, `getTitle()`

2. **Member**: Represents a library member. Each member has an ID, name, and a list of borrowed books.
   - Methods: `borrowBook()`, `returnBook()`, `getMemberInfo()`

3. **Library**: Represents the library itself, which holds a collection of books. The library can loan out and accept returns of books.
   - Methods: `addBook()`, `removeBook()`, `loanBook()`, `returnBook()`, `getAvailableBooks()`

4. **Loan**: Manages the loans (borrowing of books) between the library and its members.
   - Methods: `createLoan()`, `returnLoan()`

## Data Used

- **Books**: Titles and authors of books are inspired by Rwandan culture.
  - *Igihugu Cyacu* by Imena Jean Paul
  - *Inzira y'Ubumwe* by Mukantabana Beatha
  - *Umurage w'Abakurambere* by Rutayisire Faustin

- **Members**: The members of the library have Rwandan names, like *Kabera Jean* and *Mukandayisenga Aline*.

## How to Run the Project

1. **Prerequisites**:
   - Java Development Kit (JDK) installed.
   - IDE or text editor (e.g., IntelliJ IDEA, Eclipse, or VS Code).

2. **Steps**:
   - Open the project in your preferred IDE.
   - Compile and run the **LibraryManagementSystem** class to test the system.

3. **Sample Output**:
   - The program simulates a member borrowing and returning books, with print statements indicating the status of the operations.

## Future Improvements

- Add a user interface (UI) for easier interaction with the library system.
- Add a database for persistent storage of books, members, and loans.
- Implement more detailed error handling.
- Extend the loan period and add fine calculation for late returns.

## Contributing

Feel free to contribute to the project by suggesting improvements or feature additions. Any input is welcome!

---

This **README** provides a clear overview of the project without the unnecessary sections.

