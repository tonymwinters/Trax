package com.trax.services.role;

import com.google.gson.*;
import com.trax.dao.role.RoleDAO;
import com.trax.models.Role;
import com.trax.utilities.Alfred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
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

    private JsonDeserializer<Role> roleJsonDeserializer = new JsonDeserializer<Role>() {
        @Override
        public Role deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                JsonElement id = json.getAsJsonObject().get("id");
                JsonElement name = json.getAsJsonObject().get("name");
                JsonElement code = json.getAsJsonObject().get("code");
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
                return role;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Role.");
        }
    };

    private JsonDeserializer<ArrayList<Role>> rolesJsonDeserializer = new JsonDeserializer<ArrayList<Role>>() {
        @Override
        public ArrayList<Role> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            try {
                ArrayList<Role> roles = new ArrayList<Role>();
                for (JsonElement jsonRole : jsonElement.getAsJsonArray()) {
                    final Role role = deserializeRole(jsonRole);
                    roles.add(role);
                }
                return roles;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Roles.");
        }
    };

    public void addRole(Role role) {
        roleDAO.addRole(role);
    }

    public void updateRole(Role role) {
        roleDAO.updateRole(role);
    }

    public Role getRole(Long id) {
        return roleDAO.getRole(id);
    }

    public JsonDeserializer<Role> getRoleJsonDeserializer() {
        return roleJsonDeserializer;
    }

    @Override
    public JsonDeserializer<ArrayList<Role>> getRolesJsonDeserializer() {
        return rolesJsonDeserializer;
    }

    @Override
    public Role deserializeRole(JsonElement json) {
        Gson gson = Alfred.gsonBuilder
                .create();
        return gson.fromJson(json, Role.class);
    }

    @Override
    public List deserializeRoles(JsonElement json) {
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(ArrayList.class, getRolesJsonDeserializer())
                .create();
        return gson.fromJson(json, ArrayList.class);

    }

    public void deleteRole(Long id) {
        roleDAO.deleteRole(id);
    }

    public List<Role> getRoles() {
        return roleDAO.getRoles();
    }
}
