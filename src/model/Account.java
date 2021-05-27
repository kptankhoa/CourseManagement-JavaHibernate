package model;

import java.util.Objects;

public class Account {
    private String username;
    private String password;
    private String type;

    public Account() {
    }

    public Account(String username) {
        this.username = username;
        this.password = username;
        this.type = "student";
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.type = null;
    }

    public Account(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(username, account.username) && Objects.equals(password, account.password) && Objects.equals(type, account.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, type);
    }
}
