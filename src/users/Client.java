package users;

import db.DatabaseConnector;

public class Client extends User{

    public Client(){

    }

    public Client(Integer id, String username, String password, String firstName , String lastName, String email, String address, String role) {
        super(id, username, password, firstName, lastName, email, address, role);
    }

//    public Client login(String username, String password) {
//        Client user = (Client) db.loginUser(username, password);
//        return user;
//    }
//
//    public void register(Client user){
//        db.registerUser(user);
//    }
//
//    public String getUsername() {
//        return username;
//    }
}
