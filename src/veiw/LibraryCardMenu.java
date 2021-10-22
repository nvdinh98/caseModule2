package veiw;

import control.BookManager;
import control.LibraryCardManager;
import model.Book;
import model.LibraryCard;
import storage.LibraryCardFile;


import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class LibraryCardMenu {
    final static Scanner scanner = new Scanner(System.in);
    final static Scanner scanner1 = new Scanner(System.in);
    LibraryCardManager libraryCardManager = LibraryCardManager.getInstance();
    BookManager managerBook = BookManager.getInstance();

    //khỏi tạo singleton để tránh tạo ra một đối tượng mới khi chạy .
    private LibraryCardMenu() {
    }

    public static LibraryCardMenu getInstance() {
        return LibraryCardMenuHelper.INSTANCE;
    }

    private static class LibraryCardMenuHelper {
        private static final LibraryCardMenu INSTANCE = new LibraryCardMenu();
    }

    public void runLibraryCard() {
        try {
            libraryCardManager.setLibraryCardArrayList(LibraryCardFile.getInstance().readFile());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner number = new Scanner(System.in);


        int choice = -1;

        while (choice != 0) {
            System.out.println("--------Quản lý thẻ thư viện--------");
            System.out.println("1.Danh sách thẻ thư viện");
            System.out.println("2.Xóa thẻ thử viện");
            System.out.println("3.Tìm kiếm thẻ thư viện theo mã sinh viên");
            System.out.println("4.Thêm thẻ thư viện");
            System.out.println("5.Mượn sách");
            System.out.println("6.Trả sách");
            System.out.println("0.Quay lại");
            System.out.println("----------------図書館--------------");


            choice = number.nextInt();

            switch (choice) {
                case 1:
                    libraryCardManager.showAllLibraryCard();
                    break;

                case 2:
                    removeLibraryCard(libraryCardManager);
                    break;
                case 3:
                    searchCard();
                    break;
                case 4:
                    libraryCardManager.addLibraryCard(inputCode());
                    break;
                case 5:
                    borrowBooks();
                    break;
                case 6:
                    giveBookBack();
                    break;
                case 0:
            }
        }
    }

    //trả sách
    public void giveBookBack() {
        //thằng librarycard này để hứng thằng đối tượng mà input code tìm dc
        LibraryCard libraryCard = libraryCardManager.searchLibraryCardByCodeStudent(inputCode());
        if (libraryCard != null) {
            System.out.println("Nhập ngày, tháng, năm trả");
            LocalDate payDay = inputDates();
            boolean check = libraryCard.getBorrowedDate().isAfter(payDay);
            if (check) {
                libraryCard.setStatus(true);
                Book book = managerBook.searchBookByCode(libraryCard.getBook().getBookCode());
                // tăng đối tượng của book lên 1.
                book.setQuantity(book.getQuantity() + 1);
                System.out.println("Trả đúng hạn");
            } else {
                Book book = managerBook.searchBookByCode(libraryCard.getBook().getBookCode());
                libraryCard.setStatus(true);
                book.setQuantity(book.getQuantity() + 1);
                libraryCard.getStudent().setBalance(libraryCard.getStudent().getBalance() - 10);
                System.out.println("Trả sách quá hạn.Bạn bị phạt thêm 10 đồng!");
            }
        } else {
            System.out.println("Không có sinh viên");
        }
        assert libraryCard != null;
        System.out.println(libraryCard.show());

        try {
            libraryCardManager.getLibraryCardFile().writeFile(libraryCardManager.getLibraryCardArrayList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void borrowBooks() {
        LibraryCard libraryCard = libraryCardManager.searchLibraryCardByCodeStudent(inputCode());
        if (libraryCard != null) {
            if (libraryCard.isStatus()) {
                if (libraryCard.getStudent().getBalance() >= 40) {
                    managerBook.showAllBook();
                    System.out.print("Nhập sách code sách: ");
                    String codeBook = scanner.nextLine();
                    Book book = managerBook.searchBookByCode(codeBook);
                    if (book != null) {
                        for (int i = 0; i < managerBook.getBookArrayList().size(); i++) {
                            if (managerBook.getBookArrayList().get(i).equals(book)) {
                                libraryCard.setBook(managerBook.getBookArrayList().get(i));
                                book.setQuantity(book.getQuantity() - 1);
                            }
                        }
                        System.out.println("Nhập ngày, tháng, năm mượn: ");
                        libraryCard.setBorrowedDate(inputDates());
                        System.out.print("Nhập số ngày cần mượn: ");
                        int borrowedDays = scanner1.nextInt();
                        libraryCard.setBorrowedDays(borrowedDays);
                        libraryCard.setStatus(false);
                        //nếu mượn lâu thì trừ nhiều tiền
                        if (borrowedDays <= 7) {
                            libraryCard.getStudent().setBalance(libraryCard.getStudent().getBalance() - 10);
                        } else if (borrowedDays <= 14) {
                            libraryCard.getStudent().setBalance(libraryCard.getStudent().getBalance() - 20);
                        } else if (borrowedDays <= 21) {
                            libraryCard.getStudent().setBalance(libraryCard.getStudent().getBalance() - 25);
                        } else {
                            libraryCard.getStudent().setBalance(libraryCard.getStudent().getBalance() - 40);
                        }

                        System.out.println("Mượn sách thành công");
                    } else {
                        System.out.println("Không có sách");
                    }
                } else {
                    System.out.println("Bạn không đủ tiền. Vui lòng nạp tiền để tiếp tục!");
                }
            } else {
                System.out.println("Bạn đã mượn sách. Trả sách để tiếp tục");
            }
        } else {
            System.out.println("Không có thẻ thư viện");
        }
        System.out.println(libraryCard);

        try {
            libraryCardManager.getLibraryCardFile().writeFile(libraryCardManager.getLibraryCardArrayList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // tìm kiếm card
    private void searchCard() {
        LibraryCard libraryCard = libraryCardManager.searchLibraryCardByCodeStudent(inputCode());
        if (libraryCard != null) {
            System.out.println(libraryCard);
        } else {
            System.out.println("Không có thẻ thư viện");
        }
    }


    //xóa thẻ
    private void removeLibraryCard(LibraryCardManager libraryCardManager) {
        libraryCardManager.removeLibraryCard(inputCode());
    }


    //tạo card
    private void addLibraryCard(LibraryCard libraryCard) {
        libraryCardManager.addLibraryCard(inputCode());
    }

    //nhập code sinh viên
    private String inputCode() {
        System.out.print("Nhập code sinh viên: ");
        Scanner string = new Scanner(System.in);
        return string.nextLine();

    }

    //thêm ngày mượn
    public static LocalDate inputDates() {
        System.out.print("Năm: ");
        Scanner number = new Scanner(System.in);
        int year = number.nextInt();
        System.out.print("Tháng: ");
        int month = number.nextInt();
        System.out.print("Ngày: ");
        int day = number.nextInt();
        return LocalDate.of(year, month, day);
    }
}
