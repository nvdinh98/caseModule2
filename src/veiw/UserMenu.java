package veiw;

import control.UserManager;

import model.User;
import storage.UserFile;

import java.io.IOException;
import java.util.Scanner;

public class UserMenu {
    private UserMenu() {
    }

    private static class UserMenuHelper {
        private static final UserMenu INSTANCE = new UserMenu();
    }

    public static UserMenu getInstance() {
        return UserMenuHelper.INSTANCE;
    }

    UserManager userManager = new UserManager();
    ManagerMenu menuManager = ManagerMenu.getInstance();
    StudentManagerMenu menuStudentManager = new StudentManagerMenu();


    //Menu tổng
    public void runUserMenu() {
        Scanner scanner = new Scanner(System.in);
        try {
            userManager.setUserArrayList(UserFile.getInstance().readFile());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //khởi tạo 1 đối tượng stuent ngay trong menu
        addAdmin();
        // khởi tạo 1 đối tượng stuent ngay trong menu
        addUserStudent();
        int choice = -1;
        while (choice != 0) {
            System.out.println("------------Menu Login-------------");
            System.out.println("1. Đăng Nhập");
            System.out.println("2. Đăng kí");
            System.out.println("0. Quay trở lại ");
            System.out.println("----------------図書館--------------");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    loginUser();
                    break;
                case 2:
                    userManager.addUser(creatUser());
                    System.out.println("Đã tạo tài khoản thành công");
                    break;
                case 0:
                    System.exit(0);

            }
        }
    }
    // khai bao fix cung admin
    public void addAdmin() {
        userManager.addUser(new User("admin", "admin", "admin"));
    }

    // khai bao fix cung sinh vien
    public  void addUserStudent(){
        userManager.addUser(new User("dinh","dinh","student"));
    }
    public void loginUser() {
        Scanner string = new Scanner(System.in);
        System.out.println("Đăng nhập tài khoản");
        System.out.println("Tên tài khoản");
        String nameUser = string.nextLine();
        System.out.println("Điền mật khẩu");
        String passWord = string.nextLine();
        User user = new User(nameUser, passWord);
        boolean isLogin = userManager.isLogin(user);
        if (isLogin) {
            User user1 = userManager.findUser(nameUser);
            //nếu cái thằng user1 trùng với admin thì sẽ vào menu của admin
            if (user1.getRole().equalsIgnoreCase("admin")) {
                menuManager.runMenuAdmin();
            } else {
                //nếu cái thằng user1 trùng với admin thì sẽ vào menu của sinh viên.
               menuStudentManager.runMenuStudent();
            }
        }

    }

    // tạo 1 đối tượng student
    public User creatUser() {
        Scanner String = new Scanner(System.in);
        System.out.println("Nhập tên tài khoản muốn đăng kí");
        System.out.println("Tên đăng nhập");
        String userName = String.next();
        System.out.println("Nhập mật khẩu");
        String passWord = String.next();
        return new User(userName, passWord, "student");
    }

}
