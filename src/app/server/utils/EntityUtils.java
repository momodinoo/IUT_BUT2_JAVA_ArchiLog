package app.server.utils;

import app.server.entities.interfaces.IEntity;
import app.server.managers.database.DataManager;

import java.util.ArrayList;
import java.util.Comparator;

public class EntityUtils<T extends IEntity> {

    private final Class<T> exceptedClass;

    public EntityUtils(Class<T> exceptedClass) {
        this.exceptedClass = exceptedClass;
    }

    public ArrayList<T> getEntityList() {
        ArrayList<IEntity> values    = new ArrayList<>(DataManager.getAll().stream().sorted(Comparator.comparingInt(IEntity::getNumber)).toList());
        ArrayList<T>       documents = new ArrayList<>();

        for (IEntity document : values) {
            if (this.exceptedClass.isInstance(document)) {
                documents.add(this.exceptedClass.cast(document));
            }
        }

        return documents;
    }

    public T getEntityById(Integer id) {
        ArrayList<T> res = new ArrayList<>(this.getEntityList().stream().filter(document -> document.getNumber() == id).toList());

        if (res.size() > 0) {
            return res.get(0);
        }

        return null;
    }

}
