package services;

import dataaccess.RoleDB;
import java.util.List;
import models.Role;

/**
 *
 * @author xbali
 */

public class RoleService {
    public Role getRole(int id) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role curRole = roleDB.getRole(id);
        return curRole;
    }
    public List<Role> getRoles() throws Exception {
        RoleDB roleDB = new RoleDB();
        List<Role> roles = roleDB.getRoles();
        return roles;
    }
}