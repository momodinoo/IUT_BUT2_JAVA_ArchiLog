package app.server.managers.database;

import app.server.entities.interfaces.IEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DataManager {
    public static ArrayList<IEntity> cache = new ArrayList<>();

    public static void populate() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        DataFactory.create();
    }

    public static void add(IEntity entity) {
        cache.add(entity);
    }

    public static IEntity get(IEntity entity) {
        return cache.get(cache.indexOf(entity));
    }

    public static void print() {
        System.out.println(cache.toString());
    }



    /**
     * TODO THIS LIST:
     * Main Requests (getDVDList...) // Create Models to do that.
     * Cache System
     */

}
