package view;

import java.util.Scanner;

public class MenuManager {

    private MenuManager() {
    }

    public static MenuManager getInstance() {
        return MenuManager.MenuManagerHelper.INSTANCE;
    }

    private static class MenuManagerHelper{
        private static final MenuManager INSTANCE = new MenuManager();
    }
    public void runMenuAdmin() {
        veiw.StudentMenu studentMenuWithManagerStudent = veiw.StudentMenu.getInstance();
        veiw.BookMenu bookMenuWithManagerBook = veiw.BookMenu.getInstance();
        veiw.LibraryCardMenu libraryCardMenuWithManagerLibraryCard = veiw.LibraryCardMenu.getInstance();

        Scanner number = new Scanner(System.in);


        int choice = -1;


        while (choice != 0){
            System.out.println("--------Trang trủ--------");
            System.out.println("1. Quản lý sinh viên");
            System.out.println("2. Quản lý sách");
            System.out.println("3. Quản lý thẻ thư viện");
            System.out.println("0. Quay lại");

            choice = number.nextInt();
            switch (choice){
                case 1:
                    studentMenuWithManagerStudent.runStudent();
                    break;
                case 2:
                    bookMenuWithManagerBook.runBook();
                    break;
                case 3:
                    libraryCardMenuWithManagerLibraryCard.runLibraryCard();
                    break;
                case 0:
            }
        }

    }


}
