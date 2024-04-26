package repository;

import java.util.function.Function;

public class MemoryRepositoryByLambda<T> extends MemoryRepository<T> {

    Function<T, Integer> getIdLambda; // Я того рот наоброт как это работает? (Если работает)

    MemoryRepositoryByLambda(Function getIdLambda) {
        this.getIdLambda = getIdLambda;
    }

    @Override
    int getId(T obj) {
        return getIdLambda.apply(obj);
    }
}
