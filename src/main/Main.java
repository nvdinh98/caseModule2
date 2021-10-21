package main;


import veiw.UserMenu;

public class Main {
    public static void main(String[] args) {
        //goi instance de lay 1 du lieu duy nhat
        UserMenu userMenu = UserMenu.getInstance();
        // chay
        userMenu.runUserMenu();
    }
}
