package bibliotec;

public class Main {
    public static void main(String[] args) {
        Biblioteca app = new Biblioteca();
//        app.searchByIsbn("12453453");
//        app.searchByTitle("");
//        app.checkInBook("12453453");
//        app.addBook();
        app.checkOutBook("12312312322", 1);
    }
}