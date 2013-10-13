package com.trax.dao.role;

import com.trax.models.Role;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/13/13
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RoleDAO {

    public void addRole(Role role);
    public void updateRole(Role role);
    public Role getRole(int id);
    public void deleteRole(int id);
    public List getRoles();
}
