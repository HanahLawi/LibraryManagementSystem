import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// LibraryItem interface
interface LibraryItem {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
}

// Book class implementing LibraryItem
class Book implements LibraryItem {
    private String title;
    private String author;
    private int publicationYear;
    private boolean borrowed;

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        borrowed = true;
    }

    @Override
    public void returnItem() {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    public String getTitle() {
        return title;
    }
}

// DVD class implementing LibraryItem
class DVD implements LibraryItem {
    private String title;
    private String director;
    private int runningTime;
    private boolean borrowed;

    public DVD(String title, String director, int runningTime) {
        this.title = title;
        this.director = director;
        this.runningTime = runningTime;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        borrowed = true;
    }

    @Override
    public void returnItem() {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    public String getTitle() {
        return title;
    }
}

// Abstract LibraryUser class
abstract class LibraryUser {
    protected List<LibraryItem> borrowedItems;

    public LibraryUser() {
        borrowedItems = new ArrayList<>();
    }

    abstract void borrowItem(LibraryItem item);
    abstract void returnItem(LibraryItem item);
    abstract void printItemsBorrowed();
}

// Student class extending LibraryUser
class Student extends LibraryUser {

    @Override
    void borrowItem(LibraryItem item) {
        item.borrowItem();
        borrowedItems.add(item);
    }

    @Override
    void returnItem(LibraryItem item) {
        item.returnItem();
        borrowedItems.remove(item);
    }

    @Override
    void printItemsBorrowed() {
        System.out.println("Student's borrowed items:");
        for (LibraryItem item : borrowedItems) {
            if (item instanceof Book) {
                System.out.println("Book: " + ((Book) item).getTitle());
            } else if (item instanceof DVD) {
                System.out.println("DVD: " + ((DVD) item).getTitle());
            }
        }
    }
}

// Teacher class extending LibraryUser
class Teacher extends LibraryUser {

    @Override
    void borrowItem(LibraryItem item) {
        item.borrowItem();
        borrowedItems.add(item);
    }

    @Override
    void returnItem(LibraryItem item) {
        item.returnItem();
        borrowedItems.remove(item);
    }

    @Override
    void printItemsBorrowed() {
        System.out.println("Teacher's borrowed items:");
        for (LibraryItem item : borrowedItems) {
            if (item instanceof Book) {
                System.out.println("Book: " + ((Book) item).getTitle());
            } else if (item instanceof DVD) {
                System.out.println("DVD: " + ((DVD) item).getTitle());
            }
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating instances of Book and DVD
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        DVD dvd = new DVD("Inception", "Christopher Nolan", 148);

        System.out.println("Are you a student or a teacher? (Type 'student' or 'teacher'):");
        String userType = scanner.nextLine();

        LibraryUser user;
        if (userType.equalsIgnoreCase("student")) {
            user = new Student();
        } else if (userType.equalsIgnoreCase("teacher")) {
            user = new Teacher();
        } else {
            System.out.println("Invalid user type. Exiting...");
            return;
        }

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1. Borrow an item");
            System.out.println("2. Return an item");
            System.out.println("3. Check if an item is currently borrowed");
            System.out.println("4. Print items borrowed");
            System.out.println("5. Add Book");
            System.out.println("6. Add DVD");
            System.out.println("7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter the title of the book or DVD you want to borrow:");
                    String title = scanner.nextLine();
                    if (title.equalsIgnoreCase(book.getTitle())) {
                        user.borrowItem(book);
                    } else if (title.equalsIgnoreCase(dvd.getTitle())) {
                        user.borrowItem(dvd);
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;
                case 2:
                    System.out.println("Enter the title of the book or DVD you want to return:");
                    title = scanner.nextLine();
                    if (title.equalsIgnoreCase(book.getTitle())) {
                        user.returnItem(book);
                    } else if (title.equalsIgnoreCase(dvd.getTitle())) {
                        user.returnItem(dvd);
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;
                case 3:
                    System.out.println("Enter the title of the book or DVD you want to check:");
                    title = scanner.nextLine();
                    if (title.equalsIgnoreCase(book.getTitle())) {
                        System.out.println("Book is currently " + (book.isBorrowed() ? "borrowed" : "not borrowed"));
                    } else if (title.equalsIgnoreCase(dvd.getTitle())) {
                        System.out.println("DVD is currently " + (dvd.isBorrowed() ? "borrowed" : "not borrowed"));
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;
                case 4:
                    user.printItemsBorrowed();
                    break;
                case 5:
                    System.out.println("Enter the title of the book you want to add:");
                    String bookTitle = scanner.nextLine();
                    // Add code to create a new Book instance with the given title and add it to the library
                    System.out.println("Book added: " + bookTitle);
                    break;
                case 6:
                    System.out.println("Enter the title of the DVD you want to add:");
                    String dvdTitle = scanner.nextLine();
                    // Add code to create a new DVD instance with the given title and add it to the library
                    System.out.println("DVD added: " + dvdTitle);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
