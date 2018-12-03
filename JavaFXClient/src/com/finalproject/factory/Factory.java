package com.finalproject.factory;

import java.util.Hashtable;
import java.util.ResourceBundle;

public class Factory {

    private static Hashtable<String, Object> instances = new Hashtable<>();

    public static Object getInstance(String objName) {
        Object obj = instances.get(objName);

        try {
            if (obj == null) {
                ResourceBundle rb = ResourceBundle.getBundle("factory");
                String sClassname = rb.getString(objName);
                obj = Class.forName(sClassname).newInstance();

                // adding to cache objects
                instances.put(objName, obj);
            }
            return obj;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
