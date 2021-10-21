package control;

import model.User;
import storage.UserFile;

import java.io.IOException;
import java.util.ArrayList;

public class UserManager {
    ArrayList<User> userArrayList = new ArrayList<>();

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
        try {
            UserFile.writerFile(userArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserFile getUserFile() {
        return userFile;
    }

    public void setUserFile(UserFile userFile) {
        this.userFile = userFile;
        try {
            UserFile.writerFile(userArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    UserFile userFile = UserFile.getInstance();

    public UserManager() {
    }
    public boolean isLogin(User user){
        for (int i = 0; i < userArrayList.size(); i++) {
            if (userArrayList.get(i).getAccount().equalsIgnoreCase(user.getAccount()) &&
            userArrayList.get(i).getPassWord().equalsIgnoreCase(user.getPassWord())
            ){
                return true;
            }
        }
        return false;
    }
    public User findUser(String userName){
        User user = null;
        for (int i = 0; i < userArrayList.size(); i++) {
            if (userArrayList.get(i).getAccount().equalsIgnoreCase(userName)){
                user = userArrayList.get(i);
            }
        }
        return user;
    }
    public void addUser(User user){
        userArrayList.add(user);
        try {
            UserFile.writerFile(userArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
