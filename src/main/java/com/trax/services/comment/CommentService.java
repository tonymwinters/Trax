package com.trax.services.comment;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.trax.models.Comment;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 12/9/13
 * Time: 9:49 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommentService {

    public Comment getComment(Long id);
    public Comment saveComment(Comment comment);
    public Comment saveComment(String json);
    public Comment saveComment(JsonElement json);
    public Set saveComments(String json);
    public Set saveComments(JsonElement json);
    public JsonDeserializer<Comment> getCommentJsonDeserializer();
    public JsonDeserializer<Set<Comment>> getCommentsJsonDeserializer();
}
