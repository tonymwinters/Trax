package com.trax.services.role;

import com.google.gson.*;
import com.trax.dao.role.RoleDAO;
import com.trax.models.Role;
import com.trax.services.permission.PermissionService;
import com.trax.utilities.Alfred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private PermissionService permissionService;

    private JsonDeserializer<Role> roleJsonDeserializer = new JsonDeserializer<Role>() {
        @Override
        public Role deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                JsonElement id = json.getAsJsonObject().get("id");
                JsonElement name = json.getAsJsonObject().get("name");
                JsonElement code = json.getAsJsonObject().get("code");
                JsonElement permissions = json.getAsJsonObject().get("permissions");
                Role role = new Role();
                if (Alfred.notNull(id)) {
                    role = getRole(id.getAsLong());
                }
                if (Alfred.notNull(name)) {
                    role.setName(name.getAsString());
                }
                if (Alfred.notNull(code)) {
                    role.setCode(code.getAsString());
                }
                if (Alfred.notNull(permissions)) {
                    role.setPermissions(permissionService.savePermissions(permissions));
                }

                return role;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Role.");
        }
    };

    private JsonDeserializer<Set<Role>> rolesJsonDeserializer = new JsonDeserializer<Set<Role>>() {
        @Override
        public Set<Role> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            try {
                Set<Role> roles = new HashSet<Role>();

                for (JsonElement jsonRole : jsonElement.getAsJsonArray()) {
                    Role role = saveRole(jsonRole);
                    if(role.getId() != null){
                        roles.add(role);
                    }
                }
                return roles;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Roles.");
        }
    };

    public Role saveRole(Role role) {
        roleDAO.saveRole(role);
        return role;
    }

    public Role getRole(Long id) {
        return roleDAO.getRole(id);
    }

    public JsonDeserializer<Role> getRoleJsonDeserializer() {
        return roleJsonDeserializer;
    }

    public JsonDeserializer<Set<Role>> getRolesJsonDeserializer() {
        return rolesJsonDeserializer;
    }

    public Role saveRole(String json){
        return saveRole(new Gson().fromJson(json, JsonElement.class));
    }

    public Role saveRole(JsonElement json) {
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Role.class, getRoleJsonDeserializer())
                .create();
        return saveRole(gson.fromJson(json, Role.class));
    }

    public Set saveRoles(JsonElement json) {
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Set.class, getRolesJsonDeserializer())
                .create();
        return gson.fromJson(json, Set.class);

    }

    public void deleteRole(Long id) {
        roleDAO.deleteRole(id);
    }

    public List<Role> getRoles() {
        return roleDAO.getRoles();
    }
}
