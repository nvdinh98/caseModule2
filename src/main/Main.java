package main;


import view.MenuManager;

public class Main {
    public static void main(String[] args) {
        MenuManager menuAdminManager = MenuManager.getInstance();
        menuAdminManager.runMenuAdmin();
    }
}
