package storage;





import model.Student;

import java.io.*;
import java.util.ArrayList;

public class StudentFile {
    private static StudentFile studentFile;

    private StudentFile() {
    }
    //Singleton
    public static StudentFile getInstance(){
        if(studentFile == null){
            studentFile = new StudentFile();
        }
        return studentFile;
    }
    //đọc file
    public ArrayList<Student> readFile() throws IOException, ClassNotFoundException {
        File file = new File("studentList.dat");
        if (!file.exists()){
            file.createNewFile();
        }
        if (file.length() >0){
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            ArrayList<Student> list = (ArrayList<Student>)object;
            objectInputStream.close();
            fileInputStream.close();
            return list;
        }
        else return new ArrayList<>();
    }

    //ghì file
    public void writeFile(ArrayList<Student> students) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("studentList.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(students);
        objectOutputStream.close();
        fileOutputStream.close();
    }
}