package models;

public class user {
    private int id;
    private String name;
    private String email;
    private String userName;
    private String password;

    public user(String name, String email, String userName, String password) {
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }
}
