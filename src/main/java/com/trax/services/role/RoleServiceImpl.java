package com.trax.services.role;

import com.trax.dao.role.RoleDAO;
import com.trax.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/13/13
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDAO roleDAO;

    public void addRole(Role role) {
        roleDAO.addRole(role);
    }

    public void updateRole(Role role) {
        roleDAO.updateRole(role);
    }

    public Role getRole(Long id) {
        return roleDAO.getRole(id);
    }

    public void deleteRole(Long id) {
        roleDAO.deleteRole(id);
    }

    public List<Role> getRoles() {
        return roleDAO.getRoles();
    }
}
