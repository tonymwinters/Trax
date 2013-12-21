package com.trax.dao.permission;

import com.trax.models.Permission;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tonywinters
 * Date: 10/13/13
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PermissionDAO {

    public void savePermission(Permission permission);
    public Permission getPermission(Long id);
    public void deletePermission(Long id);
    public List<Permission> getPermissions();
}
