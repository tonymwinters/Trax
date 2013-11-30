package com.trax.utilities;

import com.google.gson.*;
import com.trax.models.Session;
import com.trax.models.User;
import com.trax.services.attendee.AttendeeService;
import com.trax.services.contact.ContactService;
import com.trax.services.owner.OwnerService;
import com.trax.services.role.RoleService;
import com.trax.services.room.RoomService;
import com.trax.services.session.SessionService;
import com.trax.services.user.UserService;
import com.trax.services.venue.VenueService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ajdanelz
 * Date: 10/19/13
 * Time: 3:10 PM
 * This is the helper class and is for utility type methods in order to
 * normalize simple function throughout the code and make it more readable
 * and easier to program
 */
 public class Alfred {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");

    public static Gson gsonSerializer = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .serializeNulls()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            // Serialize Date class
            .registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
                public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
                    return new JsonPrimitive(Alfred.dateFormat.format(date.getTime()));
                }
            })
            // Serialize SETS
            .registerTypeAdapter(Set.class, new JsonSerializer<Set>() {
                public JsonElement serialize(Set set, Type type, JsonSerializationContext context) {
                    JsonArray jsonArray = new JsonArray();
                    for (Object theObject : set) {
                        final JsonElement element = context.serialize(theObject);
                        jsonArray.add(element);
                    }
                    return jsonArray;
                }
            })
            .create();

    /**
     * Evaluates if an object is null
     * @param obj: to be evaluated
     * @return boolean true if not null
     */
    public static Boolean notNull(Object obj){
        return obj != null;
    }

    /**
     * Evaluates if an object is null
     * @param obj: to be evaluated
     * @return boolean true if not null
     */
    public static Boolean isNull(Object obj){
        return obj == null;
    }

    /**
     * Render object as json
     * @param object object to convert to json
     * @return json string
     */
    public static String renderSuccess(Object object){
        Map<Object, Object> response =  new HashMap<Object, Object>();
        response.put("success", true);
        response.put("object", object);
        return gsonSerializer.toJson(response);
    }

    /**
     * Render success as json
     * @return json string
     */
    public static String renderSuccess(){
        Map<Object, Object> response =  new HashMap<Object, Object>();
        response.put("success", true);
        return gsonSerializer.toJson(response);
    }

    /**
     * render error message as json
     * @param message error message
     * @return json string
     */
    public static String renderError(String message){
        Map<Object, Object> response =  new HashMap<Object, Object>();
        response.put("success", false);
        response.put("message", message);
        return gsonSerializer.toJson(response);
    }
}
