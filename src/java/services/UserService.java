package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author xbali
 */

public class UserService {
    public List<User> getUsers() throws Exception{
        UserDB userDB = new UserDB();
        List<User> users = userDB.getUsers();
        return users;
    }
    public User getUser(String email) throws Exception{
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        return user;
    }
    public void insertUser(String email, String fName, String lName, String pass, int newRoleId) throws Exception {
        UserDB userDB = new UserDB();
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.getRole(newRoleId);
        User user = new User(email, fName, lName, pass);
        user.setRole(role);
        userDB.insertUser(user);
    }
    public void updateUser(String email, String fName, String lName, String pass, Role role) throws Exception {
        UserDB userDB = new UserDB();
        User user = new User(email, fName, lName, pass);
        user.setRole(role);
        userDB.updateUser(user);
    }
    public void deleteUser(User user) throws Exception {
        UserDB userDB = new UserDB();
        User delUser = user;
        userDB.deleteUser(delUser);
    }
}