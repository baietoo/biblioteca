package bibliotec;

import cli.*;
import users.User;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        String raspuns;
        Scanner scanner = new Scanner(System.in);
        Biblioteca app = new Biblioteca();
        System.out.println("-----------------------------------");
        System.out.println("Bine ai venit la biblioteca de stat");
        System.out.println("-----------------------------------");

        System.out.println("Vrei sa fii mai destept?");
        System.out.println("1. Da");
        System.out.println("2. Vreau sa merg la un gratar");
        System.out.println("3. Sunt bibliotecara, cum iti permiti sa ma intrebi asa ceva?");
        raspuns = scanner.nextLine();

        if(raspuns.equals("1") || raspuns.equals("2")){
            new MeniuClient();
            MeniuClient.afisare();
        } else {
            new MeniuBibliotecar();
            MeniuBibliotecar.afisare();
        }
    }
}