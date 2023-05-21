package bibliotec;

import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    private DepozitCarti bookRepo = new DepozitCarti();

    public void searchByIsbn(String isbn) {
        System.out.println("Searching for books with ISBN: " + isbn);
        Carte carte = bookRepo.findByIsbn(isbn);
        if(carte !=null){
            System.out.println("Book found: " + carte.getTitle() + ", Author: " + carte.getAuthor() + ".");
        } else {
            System.out.println("No book found.");
        }
        System.out.println();
    }

    public void searchByTitle(String keyword){
        System.out.println("Searching for books containing keyword: " + keyword + ".");
        ArrayList<Carte> carti =  bookRepo.findByTitle(keyword);
        System.out.printf("%s Books found%s\n", carti.size(), carti.size() > 0 ? ":" : ".");
        for (Carte carte : carti){
            System.out.println( carte.getTitle() + ", Author: " + carte.getAuthor() + ";");
        }
        System.out.println();
    }

    public void checkOutBook(String isbn){
        Carte carte = bookRepo.findByIsbn(isbn);
        if(carte != null) {
            if(carte.checkOut()) {
                System.out.println("Book checked out successfully.");
                System.out.println("ISBN: " + carte.getIsbn() + ", Title: " + carte.getTitle() + ", Author: " + carte.getAuthor());
                bookRepo.updateBook(carte);
            } else {
                System.out.println("Failed to check out book");
                System.out.println("Reason: Checkout quantity > recorded quantity");
            }
        } else {
            System.out.println("Failed to check out book");
            System.out.println("No book with ISBN: " + isbn);
        }
        System.out.println();
    }

    public void checkInBook(String isbn){
        Carte carte = bookRepo.findByIsbn(isbn);
        if(carte != null) {
            carte.checkIn();
            System.out.println("Book checked in successfully.");
            System.out.println("ISBN: " + carte.getIsbn() + ", Title: " + carte.getTitle() + ", Author: " + carte.getAuthor());
            bookRepo.updateBook(carte);
        } else {
            System.out.println("Failed to check in book");
            System.out.println("No book with ISBN: " + isbn);
        }
        System.out.println();
    }

    public void addBook() {
        System.out.println("Adauga o carte: ");
        String isbn;
        String title;
        String genre;
        String description;
        String author;
        int quantity;
        int numCheckedOut;
        Scanner scanner = new Scanner(System.in);
        System.out.println("ISBN: ");
        isbn = scanner.nextLine();
        System.out.println("Title: ");
        title = scanner.nextLine();
        System.out.println("Genre: ");
        genre = scanner.nextLine();
        System.out.println("Description: ");
        description = scanner.nextLine();
        System.out.println("Author: ");
        author = scanner.nextLine();
        System.out.println("Quantity: ");
        quantity = scanner.nextInt();
        System.out.println("NumCheckedOut: ");
        numCheckedOut = scanner.nextInt();
        bookRepo.insertBook(new Carte(isbn, title, genre, description, author, quantity, numCheckedOut));
        System.out.println("Book added successfully.");
        System.out.println();
    }
}
