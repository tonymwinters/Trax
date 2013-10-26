package com.trax.utilities;

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
}
