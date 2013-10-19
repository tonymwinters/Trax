package com.trax.services.role;

import com.trax.models.Role;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/13/13
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RoleService {

    public void addRole(Role role);
    public void updateRole(Role role);
    public Role getRole(Long id);
    public void deleteRole(Long id);
    public List<Role> getRoles();
}
