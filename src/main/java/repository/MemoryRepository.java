package repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MemoryRepository<T> implements Repository<T> {
    Map<Integer, T> storage = new HashMap<>();


    @Override
    public void save(T obj) throws IOException {
        storage.put(getId(obj), obj);
    }

    @Override
    public T load(int id) throws IOException {
        return storage.get(id);
    }


    @Override
    public List<T> load(List<Integer> ids) throws IOException {

        List<T> tList = new ArrayList<>();
        for (Integer id : ids) {
            if (storage.containsKey(id)) {
                tList.add(storage.get(id));
            }
        }

        return tList;
    }

    abstract int getId(T obj);

}
