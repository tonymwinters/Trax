package com.trax.services.role;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.trax.models.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public JsonDeserializer<Role> getRoleJsonDeserializer();
    public JsonDeserializer<ArrayList<Role>> getRolesJsonDeserializer();
    public Role deserializeRole(JsonElement json);
    public List deserializeRoles(JsonElement json);
    public void deleteRole(Long id);
    public List<Role> getRoles();
}
