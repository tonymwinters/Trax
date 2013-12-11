package com.trax.dao.comment;

import com.trax.models.Comment;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/29/13
 * Time: 6:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommentDAO {

    public void saveComment(Comment comment);
    public Comment getComment(Long id);
    public void deleteComment(Long id);
}
