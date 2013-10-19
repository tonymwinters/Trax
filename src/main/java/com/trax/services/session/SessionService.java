package com.trax.services.session;

import com.trax.models.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/19/13
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SessionService {

    public void addSession(Session session);
    public void updateSession(Session session);
    public Session getSession(Long id);
    public void deleteSession(Long id);
    public List<Session> getSessions();
}
