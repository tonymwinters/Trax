package com.trax.services.session;

import com.trax.dao.session.SessionDAO;
import com.trax.models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/19/13
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
@Transactional
public class SessionServiceImpl implements SessionService{

    @Autowired
    private SessionDAO sessionDAO;

    public void addSession(Session session){
        sessionDAO.addSession(session);
    }

    public void updateSession(Session session){
        sessionDAO.updateSession(session);
    }

    public Session getSession(Long id){
        return sessionDAO.getSession(id);
    }

    public void deleteSession(Long id){
        sessionDAO.deleteSession(id);
    }

    public List<Session> getSessions(){
        return sessionDAO.getSessions();
    }

    public List<Session> byName(String query){
        return sessionDAO.byName(query);
    }
}
