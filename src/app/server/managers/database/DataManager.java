package app.server.managers.database;

import app.server.entities.DocumentEntity;
import app.server.entities.interfaces.IDocument;
import app.server.entities.interfaces.IEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class DataManager {
    public static HashMap<String, IEntity> cache = new HashMap<>();

    public static void populate() {
        DataFactory.create();
    }

    public static void add(IEntity entity) {
        cache.put(entity.getIdentifier(), entity);
    }

    public static ArrayList<IEntity> getAll() {
        return new ArrayList<>(cache.values());
    }

    public static ArrayList<IEntity> getDocuments() {
        return new ArrayList<>( cache.values().stream().filter(iEntity -> iEntity instanceof DocumentEntity).sorted((o1, o2) -> o1.getNumber() - o2.getNumber()).toList());
    }

    public static IEntity get(String entityIdentifier) {
        return cache.get(entityIdentifier);
    }

}
