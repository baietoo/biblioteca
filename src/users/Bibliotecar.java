package users;

public class Bibliotecar extends User{
    public Bibliotecar(Integer id, String username, String password, String firstName, String lastName, String email, String address, String role) {
        super(id, username, password, firstName, lastName, email, address, role);
    }

    public Bibliotecar() {

    }

    public Bibliotecar(Integer id, String username, String password, String firstName, String lastName) {
        super(id, username, password, firstName, lastName, "biblio@tecar.com", "Biblioteca Centrala", "bibliotecar");
    }

    public Bibliotecar login(String username, String password) {
        Bibliotecar bibliotecar = db.loginBibliotecar(username, password);
        return bibliotecar;
    }
    public void addBook(){

    }
}
