package cli;

import bibliotec.Biblioteca;
import users.Bibliotecar;
import bibliotec.Carte;
import users.User;

import java.util.Scanner;

public class MeniuBibliotecar {
    public static void afisare() {
        String raspuns;
        Scanner scanner = new Scanner(System.in);
        Biblioteca app = new Biblioteca();
        Bibliotecar bibliotecar = new Bibliotecar();
        if (true) {
            // login
            System.out.println("Numele contului dumneavoastra va rugam.");
            String username = scanner.nextLine();
            System.out.println("Parola daca nu va suparati?");
            String password = scanner.nextLine();
            bibliotecar = bibliotecar.login(username, password);
            if (bibliotecar.getUsername() != null) {
                System.out.println("Sa traiti doamna Bibliotecara!");
            } else {
                System.out.println("Nu aveti ce cauta aici!");
                return;
            }
        }
        raspuns = "1";
        while (raspuns.equals("1") || raspuns.equals("2") || raspuns.equals("3") || raspuns.equals("4") || raspuns.equals("5") || raspuns.equals("6")) {
            System.out.println("1. Cauta dupa ISBN");
            System.out.println("2. Cauta dupa titlu");
            System.out.println("3. Adauga o carte");
            System.out.println("4. Editeaza o carte");
            System.out.println("5. Sterge o carte");
            System.out.println("6. Iesire");
            raspuns = scanner.nextLine();
            switch (raspuns) {
                case "1":
                    System.out.println("Introduceti ISBN-ul cartii:");
                    String isbn = scanner.nextLine();
                    app.searchByIsbn(isbn);
                    break;
                case "2":
                    System.out.println("Introduceti titlul cartii(ENTER pentru a afisa toate cartile)");
                    String titlu = scanner.nextLine();
                    app.searchByTitle(titlu);
                    break;
                case "3":
                    // adauga carte
                    if(bibliotecar != null) {
                        app.addBook();
                    } else {
                        System.out.println("Nu aveti cont la noi!");
                    }
                    break;
                case "4":
                    // editeaza carte
                    if(bibliotecar != null) {
                        System.out.println("Introduceti ISBN-ul cartii:");
                        isbn = scanner.nextLine();
                        Carte carte = app.getBook(isbn);
                        app.updateBook(isbn, carte);
                    } else {
                        System.out.println("Nu aveti cont la noi!");
                    }
                    break;
                case "5":
                    // sterge carte
                    System.out.println("Introduceti ISBN-ul cartii:");
                    isbn = scanner.nextLine();
                    app.deleteBook(isbn);
                    break;
                case "6":
                    System.out.println("O zi minunata sa aveti!");
                    return;
                default:
                    System.out.println("Optiune invalida.");
            }
        }
    }
}
