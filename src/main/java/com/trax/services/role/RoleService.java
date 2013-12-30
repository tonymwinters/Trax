package com.trax.services.role;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.trax.models.Role;

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

    public Role saveRole(Role role);
    public Role getRole(Long id);
    public JsonDeserializer<Role> getRoleJsonDeserializer();
    public JsonDeserializer<Set<Role>> getRolesJsonDeserializer();
    public Role saveRole(String json);
    public Role saveRole(JsonElement json);
    public Role getRole(JsonElement json);
    public Set getRoles(JsonElement json);
    public void deleteRole(Long id);
    public List<Role> getRoles();
}
