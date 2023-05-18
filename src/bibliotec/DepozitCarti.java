package bibliotec;

import java.util.ArrayList;

public class DepozitCarti {
    private ArrayList<Carte> cartes = new ArrayList<>();

    public DepozitCarti() {
        cartes.add(new Carte("12891", "AM ramas doar FM", "mister", null,"Rad Ionescu", 3, 1));
        cartes.add(new Carte("12892", "De ce plangi?", "mister", null,"Rad Ulescu", 8, 3));
        cartes.add(new Carte("12893", "AM doar FM", "mister", null,"Rad Ionescu", 9, 1));
        cartes.add(new Carte("12894", "Am dat la FMI", "drama", null,"Stud Entu", 20, 0));
        cartes.add(new Carte("12895", "Pe loc repaus", "actiune", null,"Mili Tarescu", 13, 13));
        cartes.add(new Carte("12896", "Nu mai pot", "horror", null,"Sti Van King", 7, 1));
    }

    public Carte findByIsbn(String isbn) {
        for(Carte carte : cartes){
            if(carte.getIsbn().equals(isbn)){
                return carte;
            };
        }
        return null;
    }

    public ArrayList<Carte> findByTitle(String keyword) {
        ArrayList<Carte> booksFound = new ArrayList<>();
        for(Carte carte : cartes) {
            if (carte.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                booksFound.add(carte);
            }
        }

        return booksFound;
    }

}
