package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author xbali
 */

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        RoleService rService = new RoleService();
        UserService uService = new UserService();
        String action = request.getParameter("action");
        
        if(action != null){
            switch(action){
                case "edit" : {
                    try {
                        String email = request.getParameter("email");
                        User user = uService.getUser(email);
                        request.setAttribute("curUser", user);
                        request.setAttribute("curAction", "edit");
                    } catch(Exception e) {
                        request.setAttribute("errorMsg", "Error occuered while editing");
                    }
                    break;
                }
                case "delete" : {
                    try{
                        String email = request.getParameter("email");
                        User delUser = uService.getUser(email);
                        uService.deleteUser(delUser);
                    } catch(Exception e) {
                        request.setAttribute("errorMsg", "Error occuered while deleting user");
                    }
                    break;
                }
            }
        }
        try{
            List<User> users = uService.getUsers();
            request.setAttribute("users", users);
        } catch(Exception e) {
            request.setAttribute("errorMsg", "Error occuered while loading users");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // HttpSession session = request.getSession();
        RoleService rService = new RoleService();
        UserService uService = new UserService();
        String action = request.getParameter("action");
        
        String newEmail = request.getParameter("newEmail");
        String newFirstName = request.getParameter("newFName");
        String newLastName = request.getParameter("newLName");
        String newPass = request.getParameter("newPass");
        String newRole = request.getParameter("newRole");
        if(newEmail == null || newEmail.equals("")|| newFirstName == null || newFirstName.equals("") || newLastName == null || newLastName.equals("") || newPass == null || newPass.equals("")){
            request.setAttribute("errorMsg", "All Fields are required");
            action = null;
            try {
                List<User> users = uService.getUsers();
                request.setAttribute("users", users);
            } catch (Exception e) {
            }
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        }
        if(action != null){
            switch(action){
                case "add" : {
                    try {
                        List<User> users = uService.getUsers();
                        for(int i = 0; i < users.size(); i++){
                            if(users.get(i).getEmail().equals(newEmail)){
                                request.setAttribute("erroryMsg", "That email is already in use");
                            }
                        }
                        int newRoleId = Integer.parseInt(newRole);
                        uService.insertUser(newEmail, newFirstName, newLastName, newPass, newRoleId);
                    } catch(Exception e) {
                        request.setAttribute("errorMsg", "Error occuered while adding user");
                    }
                    break;
                }
                case "update" : {
                    try{
                        int newRoleId = Integer.parseInt(newRole);
                        Role role = new Role(newRoleId);
                        uService.updateUser(newEmail, newFirstName, newLastName, newPass, role);
                    } catch(Exception e) {
                        request.setAttribute("errorMsg", "Error occuered while updating user");
                    }
                    break;
                }
            }
        }
        
        try{
            List<User> users = uService.getUsers();
            request.setAttribute("users", users);
        } catch(Exception e) {
            
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}