import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Book Class
class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean availabilityStatus;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.availabilityStatus = true; // Initially, the book is available
    }

    public String getBookInfo() {
        return "Book ID: " + bookId + ", Title: '" + title + "', Author: " + author + ", Available: " + availabilityStatus;
    }

    public void setAvailability(boolean status) {
        this.availabilityStatus = status;
    }

    public boolean isAvailable() {
        return availabilityStatus;
    }

    public String getTitle() {
        return title;
    }
}

// Member Class
class Member {
    private int memberId;
    private String name;
    private List<Book> borrowedBooks; // List of borrowed books

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book, Library library) {
        // Check if the book is available before borrowing
        if (book.isAvailable()) {
            library.loanBook(book, this);
            borrowedBooks.add(book);
            System.out.println(name + " borrowed the book '" + book.getTitle() + "'");
        } else {
            System.out.println("Sorry, the book '" + book.getTitle() + "' is currently unavailable.");
        }
    }

    public void returnBook(Book book, Library library) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            library.returnBook(book, this);
            System.out.println(name + " returned the book '" + book.getTitle() + "'");
        } else {
            System.out.println(name + " did not borrow the book '" + book.getTitle() + "'");
        }
    }

    public String getMemberInfo() {
        // List borrowed books if any
        StringBuilder booksInfo = new StringBuilder();
        for (Book book : borrowedBooks) {
            booksInfo.append(book.getTitle()).append(", ");
        }
        String borrowedBooksTitles = borrowedBooks.isEmpty() ? "None" : booksInfo.toString();
        return "Member ID: " + memberId + ", Name: " + name + ", Borrowed Books: " + borrowedBooksTitles;
    }
}

// Library Class
class Library {
    private List<Book> books; // Collection of books in the library

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book '" + book.getTitle() + "' has been added to the library.");
    }

    public void removeBook(int bookId) {
        Book book = books.stream().filter(b -> b.getBookInfo().contains("Book ID: " + bookId)).findFirst().orElse(null);
        if (book != null) {
            books.remove(book);
            System.out.println("Book '" + book.getTitle() + "' has been removed from the library.");
        } else {
            System.out.println("Book with ID " + bookId + " not found.");
        }
    }

    public void loanBook(Book book, Member member) {
        book.setAvailability(false);
        Loan.createLoan(book, member);
    }

    public void returnBook(Book book, Member member) {
        book.setAvailability(true);
        Loan.returnLoan(book, member);
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
}

// Loan Class
class Loan {
    private static int loanCounter = 0;
    private int loanId;
    private Book book;
    private Member member;
    private Date loanDate;
    private Date dueDate;

    public Loan(Book book, Member member, Date loanDate, Date dueDate) {
        this.loanId = ++loanCounter;
        this.book = book;
        this.member = member;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public static void createLoan(Book book, Member member) {
        Date loanDate = new Date();
        Date dueDate = new Date(loanDate.getTime() + (14L * 24 * 60 * 60 * 1000)); // 14 days loan period
        Loan loan = new Loan(book, member, loanDate, dueDate);
        System.out.println("Loan created: " + member.getMemberInfo() + " borrowed '" + book.getTitle() + "' until " + dueDate);
    }

    public static void returnLoan(Book book, Member member) {
        System.out.println("Loan closed: " + member.getMemberInfo() + " returned '" + book.getTitle() + "'");
    }
}

// Main Class for Testing
public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Create some books (Rwandan-written books)
        Book book1 = new Book(1, "Igihugu Cyacu", "Imena Jean Paul");
        Book book2 = new Book(2, "Inzira y'Ubumwe", "Mukantabana Beatha");
        Book book3 = new Book(3, "Umurage w'Abakurambere", "Rutayisire Faustin");

        // Create a library and add books
        Library library = new Library();
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Create members (Rwandan names)
        Member member1 = new Member(101, "Kabera Jean");
        Member member2 = new Member(102, "Mukandayisenga Aline");

        // Borrow and return books
        member1.borrowBook(book1, library);
        member2.borrowBook(book1, library); // Book already borrowed by Kabera
        member1.returnBook(book1, library);
        member2.borrowBook(book1, library); // Now available for Mukandayisenga
        member2.returnBook(book1, library);

        // Check available books
        System.out.println("\nAvailable Books in Library:");
        for (Book book : library.getAvailableBooks()) {
            System.out.println(book.getBookInfo());
        }

        // Check member info
        System.out.println("\nMember Info:");
        System.out.println(member1.getMemberInfo());
        System.out.println(member2.getMemberInfo());
    }
}
