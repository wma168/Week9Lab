<%-- 
    Document   : users
    Created on : 19-Mar-2023, 3:00:56 PM
    Author     : xbali
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
        <h1>Manage Users</h1>
        <c:if test="${users.size() > 0}">
            <table border="1">
            <tr>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Role</th>
                <th>Edit User</th>
                <th>Delete User</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.role.roleName}</td>
                    <td>
                        <c:url value="/users" var="editUser">
                            <c:param name="email" value="${user.email}"/>
                            <c:param name="action" value="edit"/>
                        </c:url>
                        <a href=${editUser}>Edit</a>
                    </td>
                    <td>
                        <c:url value="/users" var="deleteUser">
                            <c:param name="email" value="${user.email}"/>
                            <c:param name="action" value="delete"/>
                        </c:url>
                        <a href=${deleteUser}>Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </table>
        </c:if>
        
        <c:if test="${users.size() == 0}">
            <h3>No users found. Please add a user.</h3>
        </c:if>
        
        <c:if test="${curUser eq null}">
            <h2>Add User</h2>
            <form method="post" action="users">
                Email: <input type="text" name="newEmail"><br>
                First name: <input type="text" name="newFName"><br>
                Last name: <input type="text" name="newLName"><br>
                Password: <input type="password" name="newPass"><br>
                Role: <select name="newRole">
                    <option value="1">system admin</option>
                    <option value="2">regular user</option>
                </select>
                <input type="submit" value="Add user">
                <input type="hidden" name="action" value="add">
            </form>
        </c:if>
            
        <c:if test="${curAction eq 'edit'}">
            <h2>Edit User</h2>
            <form method="post" action="users">
                Email: ${curUser.email}<input type="hidden" name="newEmail" value="${curUser.email}"><br>
                First name: <input type="text" name="newFName" value="${curUser.firstName}"><br>
                Last name: <input type="text" name="newLName" value="${curUser.lastName}"><br>
                Password: <input type="password" name="newPass"><br>
                Role: <select name="newRole">
                    <c:if test="${curUser.role.roleId == 1}">
                        <option value="1" selected>system admin</option>
                        <option value="2">regular user</option>
                    </c:if>
                    <c:if test="${curUser.role.roleId == 2}">
                        <option value="1">system admin</option>
                        <option value="2" selected>regular user</option>
                    </c:if>
                </select><br>
                <input type="submit" value="Update">
                <input type="hidden" name="action" value="update"> 
            </form>
                <a href="/"><input type="submit" name="action" value="Cancel"></a>
        </c:if>
            <p>
                ${errorMsg}
            </p>
    </body>
</html>