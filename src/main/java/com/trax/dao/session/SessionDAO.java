package com.trax.dao.session;

import com.trax.models.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/19/13
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SessionDAO {

    public void saveSession(Session session);
    public Session getSession(Long id);
    public void deleteSession(Long id);
    public List<Session> getSessions();
    public List<Session> byName(String query);
}
