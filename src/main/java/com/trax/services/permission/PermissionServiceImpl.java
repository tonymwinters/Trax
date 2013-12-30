package com.trax.services.permission;

import com.google.gson.*;
import com.trax.dao.permission.PermissionDAO;
import com.trax.models.Permission;
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
 * User: ajdanelz
 * Date: 12/29/13
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionDAO permissionDAO;

    private JsonDeserializer<Permission> permissionJsonDeserializer = new JsonDeserializer<Permission>() {
        @Override
        public Permission deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                JsonElement id = json.getAsJsonObject().get("id");
                JsonElement name = json.getAsJsonObject().get("name");
                JsonElement code = json.getAsJsonObject().get("code");
                JsonElement description = json.getAsJsonObject().get("description");
                Permission permission = new Permission();
                if (Alfred.notNull(id)) {
                    permission = getPermission(id.getAsLong());
                }
                if (Alfred.notNull(name)) {
                    permission.setName(name.getAsString());
                }
                if (Alfred.notNull(code)) {
                    permission.setCode(code.getAsString());
                }
                if (Alfred.notNull(description)) {
                    permission.setDescription(description.getAsString());
                }

                return permission;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Permission.");
        }
    };

    private JsonDeserializer<Set<Permission>> permissionsJsonDeserializer = new JsonDeserializer<Set<Permission>>() {
        @Override
        public Set<Permission> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            try {
                Set<Permission> permissions = new HashSet<Permission>();

                for (JsonElement jsonPermission : jsonElement.getAsJsonArray()) {
                    Permission permission = getPermission(jsonPermission);
                    if(permission.getId() != null){
                        permissions.add(permission);
                    }
                }
                return permissions;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Permissions.");
        }
    };

    public Permission savePermission(Permission permission) {
        permissionDAO.savePermission(permission);
        return permission;
    }

    public Permission getPermission(Long id) {
        return permissionDAO.getPermission(id);
    }

    public JsonDeserializer<Permission> getPermissionJsonDeserializer() {
        return permissionJsonDeserializer;
    }

    public JsonDeserializer<Set<Permission>> getPermissionsJsonDeserializer() {
        return permissionsJsonDeserializer;
    }

    public Permission savePermission(String json){
        return savePermission(new Gson().fromJson(json, JsonElement.class));
    }

    public Permission getPermission(JsonElement json) {
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Permission.class, getPermissionJsonDeserializer())
                .create();
        return gson.fromJson(json, Permission.class);
    }

    public Permission savePermission(JsonElement json) {
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Permission.class, getPermissionJsonDeserializer())
                .create();
        return savePermission(gson.fromJson(json, Permission.class));
    }

    public Set getPermissions(JsonElement json) {
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Set.class, getPermissionsJsonDeserializer())
                .create();
        return gson.fromJson(json, Set.class);

    }

    public void deletePermission(Long id) {
        permissionDAO.deletePermission(id);
    }

    public List<Permission> getPermissions() {
        return permissionDAO.getPermissions();
    }
}