package com.trax.services.comment;

import com.google.gson.*;
import com.trax.dao.comment.CommentDAO;
import com.trax.models.Comment;
import com.trax.services.user.UserService;
import com.trax.utilities.Alfred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 12/9/13
 * Time: 9:49 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private UserService userService;



    private JsonDeserializer<Comment> commentJsonDeserializer = new JsonDeserializer<Comment>() {
        @Override
        public Comment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                JsonElement id = json.getAsJsonObject().get("id");
                JsonElement content = json.getAsJsonObject().get("content");
                JsonElement user = json.getAsJsonObject().get("user");
                Comment comment = new Comment();
                if (Alfred.notNull(id)) {
                    comment = getComment(id.getAsLong());
                }
                if (Alfred.notNull(content)) {
                    comment.setContent(content.getAsString());
                }
                if (Alfred.notNull(user)) {
                    comment.setUser(userService.saveUser(user));
                }

                saveComment(comment);
                return comment;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Comment.");
        }
    };

    private JsonDeserializer<Set<Comment>> commentsJsonDeserializer = new JsonDeserializer<Set<Comment>>() {
        @Override
        public Set<Comment> deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                Set<Comment> comments = new HashSet<Comment>();
                for (JsonElement jsonComment : jsonElement.getAsJsonArray()) {
                    comments.add(saveComment(jsonComment));
                }
                return comments;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Comments.");
        }
    };

    @Override
    public Comment getComment(Long id) {
        return commentDAO.getComment(id);
    }

    public Comment saveComment(Comment comment){
        commentDAO.saveComment(comment);
        return comment;
    }

    public Comment saveComment(String json) {
        return saveComment(new Gson().fromJson(json, JsonElement.class));
    }

    public Comment saveComment(JsonElement json) {
        Gson gson = Alfred.gsonBuilder
            .registerTypeAdapter(Comment.class, getCommentJsonDeserializer())
            .create();

        return gson.fromJson(json, Comment.class);
    }

    public Set saveComments(String json) {
        return saveComments(new Gson().fromJson(json, JsonElement.class));
    }

    public Set saveComments(JsonElement json) {
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Set.class, getCommentsJsonDeserializer())
                .create();

        return gson.fromJson(json, Set.class);
    }

    public JsonDeserializer<Comment> getCommentJsonDeserializer() {
        return commentJsonDeserializer;
    }

    public JsonDeserializer<Set<Comment>> getCommentsJsonDeserializer() {
        return commentsJsonDeserializer;
    }
}
