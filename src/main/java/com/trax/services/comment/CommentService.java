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
    public Comment deserializeComment(String json);
    public Comment deserializeComment(JsonElement json);
    public Set deserializeComments(String json);
    public Set deserializeComments(JsonElement json);
    public JsonDeserializer<Comment> getCommentJsonDeserializer();
    public JsonDeserializer<Set<Comment>> getCommentsJsonDeserializer();
}
