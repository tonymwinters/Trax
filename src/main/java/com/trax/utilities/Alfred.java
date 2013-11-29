package com.trax.utilities;

import com.google.gson.*;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.sql.Date;
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

    public static Gson gson = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .serializeNulls()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
                    // Serialize Date class
            .registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
                public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
                    return new JsonPrimitive(sdf.format(date.getTime()));
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
        return gson.toJson(response);
    }

    /**
     * Render success as json
     * @return json string
     */
    public static String renderSuccess(){
        Map<Object, Object> response =  new HashMap<Object, Object>();
        response.put("success", true);
        return gson.toJson(response);
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
        return gson.toJson(response);
    }
}
