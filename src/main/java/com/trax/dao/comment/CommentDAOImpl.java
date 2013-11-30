package com.trax.dao.comment;

import com.trax.models.Comment;
import com.trax.utilities.Alfred;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/29/13
 * Time: 6:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private org.hibernate.Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addComment(Comment comment){
        getCurrentSession().save(comment);
    }
    public void updateComent(Comment comment){
        getCurrentSession().update(comment);
    }
    public Comment getComment(Long id){
        return (Comment) getCurrentSession().get(Comment.class, id);
    }
    public void deleteComment(Long id){
        Comment comment = getComment(id);
        if(Alfred.notNull(comment))
            getCurrentSession().delete(comment);
    }
}
