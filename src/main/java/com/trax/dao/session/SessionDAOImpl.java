package com.trax.dao.session;

import com.trax.models.Session;
import com.trax.utilities.Alfred;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/19/13
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class SessionDAOImpl implements SessionDAO{

    @Autowired
    private SessionFactory sessionFactory;

    private org.hibernate.Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void saveSession(Session session){
        getCurrentSession().saveOrUpdate(session);
    }

    public Session getSession(Long id){
        return (Session) getCurrentSession().get(Session.class, id);
    }

    public void deleteSession(Long id){
        Session session = getSession(id);
        if(Alfred.notNull(session))
            getCurrentSession().delete(session);
    }

    public List<Session> getSessions(){
        return getCurrentSession().createQuery("from Session").list();
    }

    public List<Session> byName(String query){
        return getCurrentSession().createQuery("from Session where lower(name) like '%"+query+"%'").list();
    }
}
