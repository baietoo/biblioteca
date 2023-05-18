package bibliotec;

import java.util.ArrayList;

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
        ArrayList<Carte> cartes =  bookRepo.findByTitle(keyword);
        System.out.printf("%s Books found%s\n", cartes.size(), cartes.size() > 0 ? ":" : ".");
        for (Carte carte : cartes){
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

        } else {
            System.out.println("Failed to check in book");
            System.out.println("No book with ISBN: " + isbn);
        }
        System.out.println();
    }

}
