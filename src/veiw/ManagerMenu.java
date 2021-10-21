package veiw;

import java.util.Scanner;

public class ManagerMenu {

    private ManagerMenu() {
    }

    public static ManagerMenu getInstance() {
        return ManagerMenu.MenuManagerHelper.INSTANCE;
    }

    private static class MenuManagerHelper{
        private static final ManagerMenu INSTANCE = new ManagerMenu();
    }
    public void runMenuAdmin() {
        StudentMenu studentMenu = StudentMenu.getInstance();
        BookMenu bookMenu = veiw.BookMenu.getInstance();

        LibraryCardMenu libraryCardMenu = LibraryCardMenu.getInstance();

        Scanner number = new Scanner(System.in);


        int choice = -1;

        while (choice != 0){
            System.out.println("------------Trang trủ-------------");
            System.out.println("1.Quản lý sinh viên");
            System.out.println("2.Quản lý sách");
            System.out.println("3.Quản lý thẻ thư viện");
            System.out.println("0.Quay lại");
            System.out.println("----------------図書館--------------");

            choice = number.nextInt();
            switch (choice){
                case 1:
                    studentMenu.runStudent();
                    break;
                case 2:
                    bookMenu.runBook();
                    break;
                case 3:
                    libraryCardMenu.runLibraryCard();
                    break;
                case 0:
            }
        }

    }


}
