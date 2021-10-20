package control;

import model.LibraryCard;
import model.Student;
import storage.LibraryCardFile;
import java.io.IOException;
import java.util.ArrayList;


public class LibraryCardManager {
    ArrayList<LibraryCard> libraryCardArrayList = new ArrayList<>();
    LibraryCardFile libraryCardFile = LibraryCardFile.getInstance();

    private static final StudentManager MANAGER_STUDENT = StudentManager.getInstance();


    private LibraryCardManager() {
    }

    public static LibraryCardManager getInstance() {
        return LibraryCardManager.ManagerLibraryCardHelper.INSTANCE;
    }

    private static class ManagerLibraryCardHelper{
        private static final LibraryCardManager INSTANCE = new LibraryCardManager();
    }
    public ArrayList<LibraryCard> getLibraryCardArrayList() {
        return libraryCardArrayList;
    }

    public void setLibraryCardArrayList(ArrayList<LibraryCard> libraryCardArrayList) {
        this.libraryCardArrayList = libraryCardArrayList;
    }

    public LibraryCardFile getLibraryCardFile() {
        return libraryCardFile;
    }

    public void setLibraryCardFile(LibraryCardFile libraryCardFile) {
        this.libraryCardFile = libraryCardFile;
    }

    //thêm card theo code
    public void addLibraryCard(String code) {
        Student student = MANAGER_STUDENT.searchStudentByCode(code);
        boolean check = false;
        if (student != null) {

            for (LibraryCard libraryCard : libraryCardArrayList) {
                if (libraryCard.getStudent().getStudentCode().equalsIgnoreCase(code)) {
                    check = true;
                    System.out.println("Sinh viên đã có thẻ thư viện");
                }
            }
            if (!check) {
                libraryCardArrayList.add(new LibraryCard(student));
                System.out.println("Tạo thẻ thành công");
            }
        } else {
            System.out.println("Không tìm thấy sinh viên");
        }

        try {
            libraryCardFile.writeFile(getLibraryCardArrayList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //xóa card theo code
    public void removeLibraryCard(String code) {
        Student student = MANAGER_STUDENT.searchStudentByCode(code);
        if (student != null) {
            for (int i = 0; i < libraryCardArrayList.size(); i++) {
                if (libraryCardArrayList.get(i).getStudent().equals(student)) {
                    libraryCardArrayList.remove(i);
                }
            }
        } else {
            System.out.println("Không tìm thấy sinh viên");
        }
        try {
            libraryCardFile.writeFile(getLibraryCardArrayList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //showAll danh sách card
    public void showAllLibraryCard() {
        for (LibraryCard libraryCard : libraryCardArrayList) {
            System.out.println(libraryCard);
        }
    }

    //tìm kiếm card theo code
    public LibraryCard searchLibraryCardByCodeStudent(String code) {

        for (LibraryCard libraryCard : libraryCardArrayList) {
            if (libraryCard.getStudent().getStudentCode().equalsIgnoreCase(code)) {
                return libraryCard;
            }
        }
        return null;
    }



}
