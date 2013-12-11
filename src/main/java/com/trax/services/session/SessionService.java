package com.trax.services.session;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.trax.models.Session;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/19/13
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SessionService {

    public Session saveSession(Session session);
    public Session getSession(Long id);
    public Session saveSession(String json);
    public Session saveSession(JsonElement json);
    public Set saveSessions(String json);
    public Set saveSessions(JsonElement json);
    public JsonDeserializer<Session> getSessionJsonDeserializer();
    public JsonDeserializer<Set<Session>> getSessionsJsonDeserializer();
    public void deleteSession(Long id);
    public List<Session> getSessions();
    public List<Session> byName(String query);
}
