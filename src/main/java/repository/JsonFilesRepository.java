package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class JsonFilesRepository<T> implements Repository<T> {

    ObjectMapper mapper;

    TypeReference<T> typeReference;

    File dir;

    Function<T, Integer> getIdLambda;

    public JsonFilesRepository(ObjectMapper mapper, TypeReference<T> typeReference, File dir, Function<T, Integer> getIdLambda) {
        this.mapper = mapper;
        this.typeReference = typeReference;
        this.dir = dir;
        this.getIdLambda = getIdLambda;
    }

    @Override
    public void save(T obj) throws IOException {

        mapper.writeValue(new File(dir.getPath() + "/" + getIdLambda), obj);

    }

    @Override
    public T load(int id) throws IOException {

        File dirId = new File(dir.getPath() + "/" + id);
        return mapper.readValue(dirId, typeReference);
    }

    @Override
    public List<T> load(List<Integer> ids) throws IOException {
        List<T> list = new ArrayList<>();

        for (Integer id : ids) {
            list.add(load(id));
        }
        return list;
    }

    private List<Integer> getAllIds() {
        File[] files = dir.listFiles();
        List<Integer> fileIds = new ArrayList<>();
        for (File file : Objects.requireNonNull(files)) { // Objects.requireNonNull - IDEA suggestion
            fileIds.add(Integer.parseInt(file.getName()));
        }
        return fileIds;
    }

    @Override
    public List<T> loadAll() throws IOException {
        return load(getAllIds());
    }
}
