package com.trax.utilities;

import com.google.gson.*;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    public static GsonBuilder gsonBuilder = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .setPrettyPrinting();

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

    public static Gson gsonDeserializer = new GsonBuilder()
            //Date
            .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    try {
                        return json == null ? null : Alfred.dateFormat.parse(json.getAsString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    throw new JsonParseException("Could not parse date.");
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
