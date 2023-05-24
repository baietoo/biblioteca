package bibliotec;

import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    private DepozitCarti bookRepo = new DepozitCarti();

    public void searchByIsbn(String isbn) {
        System.out.println("Cautam carti cu ISBN: " + isbn);
        Carte carte = bookRepo.findByIsbn(isbn);
        if(carte !=null){
            System.out.println("Carte gasita: " + carte.getTitle() + ", Author: " + carte.getAuthor() + ".");
        } else {
            System.out.println("Nu am gasit cartea.");
        }
        System.out.println();
    }

    public void searchByTitle(String keyword){
        System.out.println("Cautam carti care sa contina: " + keyword + ".");
        ArrayList<Carte> carti =  bookRepo.findByTitle(keyword);
        System.out.printf("%s Carti gasite%s\n", carti.size(), carti.size() > 0 ? ":" : ".");
        for (Carte carte : carti){
            System.out.println( "ISBN: " + carte.getIsbn() + ", " + carte.getTitle() + ", Author: " + carte.getAuthor() + ";");
        }
        System.out.println();
    }

    public void checkOutBook(String isbn, Integer id_client){
        Carte carte = bookRepo.findByIsbn(isbn);
//        TODO: implement get client function
        if(carte != null) {
            if(carte.checkOut()) {
                System.out.println("Lectura placuta! 30 de zile ai la dispozitie cartea.");
                System.out.println("ISBN: " + carte.getIsbn() + ", Titlu: " + carte.getTitle() + ", Autor: " + carte.getAuthor());
                bookRepo.updateBook(carte, id_client);

            } else {
                System.out.println("NU AVEM CARTEA IN STOC!!!");
                System.out.println("Pentru informatii extra mergeti la etajul 1 ghiseul 3,14.");
            }
        } else {
            System.out.println("Nu avem cartea asta!!!");
            System.out.println("ISBN: " + isbn);
        }
        System.out.println();
    }

    public void checkInBook(String isbn, Integer id_client){
        Carte carte = bookRepo.findByIsbn(isbn);
        if(carte != null) {
            carte.checkIn();
            System.out.println("In sfarsit ati adus cartea inapoi...");
            System.out.println("ISBN: " + carte.getIsbn() + ", Titlu: " + carte.getTitle() + ", Autor: " + carte.getAuthor());
            bookRepo.updateBook(carte, id_client);
        } else {
            System.out.println("Nu putem primi cartea ca nu o avem in stoc!!!");
            System.out.println("ISBN: " + isbn);
        }
        System.out.println();
    }

    public void addBook() {
        System.out.println("Cu respect doamna bibliotecara, va rog sa adaugati o carte.");
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
        System.out.println("Titlu: ");
        title = scanner.nextLine();
        System.out.println("Gen: ");
        genre = scanner.nextLine();
        System.out.println("Descriere: ");
        description = scanner.nextLine();
        System.out.println("Autor: ");
        author = scanner.nextLine();
        System.out.println("Cantitate: ");
        quantity = scanner.nextInt();
        System.out.println("carti rezervate: ");
        numCheckedOut = scanner.nextInt();
        bookRepo.insertBook(new Carte(isbn, title, genre, description, author, quantity, numCheckedOut));
        System.out.println("Carte adaugata cu succes.");
        System.out.println();
    }

    public Carte getBook(String isbn) {
        return bookRepo.findByIsbn(isbn);
    }

    public void updateBook(String isbn, Carte carte) {
        System.out.println("Cu respect doamna bibliotecara, va rog sa modificati o carte.");
        System.out.println("Modifica o carte: ");
        String title;
        String genre;
        String description;
        String author;
        int quantity;
        int numCheckedOut;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Titlu: " + carte.getTitle());
        title = scanner.nextLine();
        System.out.println("Gen: " + carte.getGenre());
        genre = scanner.nextLine();
        System.out.println("Descriere: " + carte.getDescription());
        description = scanner.nextLine();
        System.out.println("Autor: " + carte.getAuthor());
        author = scanner.nextLine();
        System.out.println("Cantitate: " + carte.getQuantity());
        quantity = scanner.nextInt();
        System.out.println("carti rezervate: " + carte.getNumCheckedOut());
        numCheckedOut = scanner.nextInt();
        bookRepo.updateBook(new Carte(isbn, title, genre, description, author, quantity, numCheckedOut));
        System.out.println("Carte modificata cu succes.");
        System.out.println();
    }

    public void deleteBook(String isbn) {
        System.out.println("Cu respect doamna bibliotecara, va rog sa stergeti o carte.");
        System.out.println("Sterge o carte: ");
        bookRepo.deleteBook(isbn);
        System.out.println("Carte stearsa cu succes.");
        System.out.println();
    }
}
