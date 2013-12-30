package com.trax.services.permission;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.trax.models.Permission;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 12/29/13
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PermissionService {

    public Permission savePermission(Permission permission);
    public Permission getPermission(Long id);
    public JsonDeserializer<Permission> getPermissionJsonDeserializer();
    public JsonDeserializer<Set<Permission>> getPermissionsJsonDeserializer();
    public Permission savePermission(String json);
    public Permission savePermission(JsonElement json);
    public Permission getPermission(JsonElement json);
    public Set getPermissions(JsonElement json);
    public void deletePermission(Long id);
    public List<Permission> getPermissions();
}