package storage;

import model.User;

import java.io.*;
import java.util.ArrayList;

public class UserFile {
    private static UserFile userFile;

    private UserFile() {
    }

    public static UserFile getInstance() {
        if (userFile == null) {
            userFile = new UserFile();
        }
        return userFile;
    }

    public static  ArrayList<User> readFile() throws IOException, ClassNotFoundException {
        File file = new File("user.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        if (file.length() > 0) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            ArrayList<User> list = (ArrayList<User>) object;
            objectInputStream.close();
            fileInputStream.close();
            return list;
        } else return new ArrayList<>();
    }
    public static  void writerFile(ArrayList<User> users) throws IOException {
        File file;
        FileOutputStream fileOutputStream = new FileOutputStream("user.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(users);
        objectOutputStream.close();
        fileOutputStream.close();
    }
}

