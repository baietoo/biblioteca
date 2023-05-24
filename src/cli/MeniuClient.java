package cli;
import users.User;
import java.util.Scanner;

import bibliotec.Biblioteca;

public  class MeniuClient {
    public static void afisare() {
        String raspuns;
        Scanner scanner = new Scanner(System.in);
        Biblioteca app = new Biblioteca();
        System.out.println("Ai cont la noi?");
        System.out.println("1. Da");
        System.out.println("2. Gratar va rog");
        raspuns = scanner.nextLine();

        System.out.println("1. Logheaza-te odata si mai citeste ceva");
        System.out.println("2. creaza-ti cont si mai citeste ceva");
        System.out.println("3. Iesire");
        raspuns = scanner.nextLine();
        User client = new User();
        switch (raspuns) {
            case "1":
                // login
                System.out.println("Introduceti username-ul pana nu inchidem:");
                String username = scanner.nextLine();
                System.out.println("parola(nu a fost buget pt parole ascunse)?");
                String password = scanner.nextLine();
                client = client.login(username, password);
                if (client.getUsername() != null) {
                    System.out.println("Bine ai venit(la 15:30 inchidem)!");
                } else {
                    System.out.println("Username-ul nu exista!");
                }
                break;
            case "2":
                // register
                System.out.println("Introduceti username-ul:");
                client.setUsername( scanner.nextLine());
                System.out.println("parola(nu a fost buget pt parole ascunse)?");
                client.setPassword(scanner.nextLine());
                System.out.println("Numele:");
                client.setLastName(scanner.nextLine());
                System.out.println("Prenumele:");
                client.setFirstName(scanner.nextLine());
                System.out.println("Email:");
                client.setEmail(scanner.nextLine());
                client.register(client);
                System.out.println("Bine ai venit(la 15:30 inchidem)!");
                break;
            case "3":
                System.out.println("Am incercat...");
                return;
            default:
                System.out.println("1, 2 sau 3! E asa greu?");
        }

        while (raspuns.equals("1") || raspuns.equals("2") || raspuns.equals("3") || raspuns.equals("4")) {
            System.out.println("1. Cauta dupa ISBN");
            System.out.println("2. Cauta dupa titlu");
            System.out.println("3. Inchiriaza o carte");
            System.out.println("4. Returneaza o carte");
            System.out.println("5. Iesire");
            raspuns = scanner.nextLine();
            switch (raspuns) {
                case "1":
                    System.out.println("Introduceti ISBN-ul cartii:");
                    String isbn = scanner.nextLine();
                    app.searchByIsbn(isbn);
                    break;
                case "2":
                    System.out.println("Introduceti titlul cartii:");
                    String titlu = scanner.nextLine();
                    app.searchByTitle(titlu);
                    break;
                case "3":
                    if(client != null) {
                        System.out.println("Introduceti ISBN-ul cartii:");
                        isbn = scanner.nextLine();
                        app.checkOutBook(isbn, client.getId());
                    } else {
                        System.out.println("Nu aveti cont la noi!");
                    }
                    break;
                case "4":
                    if(client != null) {
                        System.out.println("Introduceti ISBN-ul cartii:");
                        isbn = scanner.nextLine();
                        app.checkInBook(isbn, client.getId());
                    } else {
                        System.out.println("Nu aveti cont la noi!");
                    }
                    break;
                case "5":
                    System.out.println("Oricum inchideam!");
                    return;
                default:
                    System.out.println("Optiune invalida!");
            }
        }
    }
}


