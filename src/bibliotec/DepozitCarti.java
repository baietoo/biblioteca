package bibliotec;
import db.DatabaseConnector;

import java.util.ArrayList;

public class DepozitCarti {
    private ArrayList<Carte> carti = new ArrayList<>();
    private final DatabaseConnector db = DatabaseConnector.getInstance();
    public DepozitCarti() {
    }

    public Carte findByIsbn(String isbn) {
        Carte carte = db.findByIsbn(isbn);
        return carte;
    }

    public ArrayList<Carte> findByTitle(String keyword) {
        ArrayList<Carte> booksFound = new ArrayList<>();
        booksFound = db.findByTitle(keyword);
        return booksFound;
    }

    public void insertBook(Carte carte){
        db.insertBook(carte);
    }

    public void updateBook(Carte carte) {
        db.updateBook(carte);
    }
}
