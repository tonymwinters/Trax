package com.trax.services.attendee;

import com.google.gson.*;
import com.trax.dao.attendee.AttendeeDAO;
import com.trax.models.Attendee;
import com.trax.models.Session;
import com.trax.models.User;
import com.trax.services.session.SessionService;
import com.trax.services.user.UserService;
import com.trax.utilities.Alfred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 11/24/13
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class AttendeeServiceImpl implements AttendeeService {

    @Autowired
    private AttendeeDAO attendeeDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    private JsonDeserializer<Attendee> attendeeJsonDeserializer = new JsonDeserializer<Attendee>() {
        @Override
        public Attendee deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                JsonElement id = json.getAsJsonObject().get("id");
                if (Alfred.isNull(id)) {
                    return deserializeAttendee(json.toString());
                } else {
                    return getAttendee(id.getAsLong());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw new JsonParseException("Could not deserialize Attendee.");
        }
    };


    public void addAttendee(Attendee attendee){
        attendeeDAO.addAttendee(attendee);
    }

    public void updateAttendee(Attendee attendee){
        attendeeDAO.updateAttendee(attendee);
    }

    public Attendee getAttendee(Long id){
        return attendeeDAO.getAttendee(id);
    }

    public Attendee deserializeAttendee(String json){
        Gson gson = Alfred.gsonBuilder
                .registerTypeAdapter(Session.class, sessionService.getSessionJsonDeserializer())
                .registerTypeAdapter(User.class, userService.getUserJsonDeserializer())
                .create();

        return gson.fromJson(json, Attendee.class);
    }

    public JsonDeserializer<Attendee> getAttendeeJsonDeserializer(){
        return attendeeJsonDeserializer;
    }

    public void deleteAttendee(Long id){
        attendeeDAO.deleteAttendee(id);
    }

    public List getAttendees(){
        return attendeeDAO.getAttendees();
    }

    public List bySessionAndFullName(Long id, String query){
        return attendeeDAO.bySessionAndFullName(id, query);
    }
}
