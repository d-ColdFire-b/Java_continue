package factory;

public abstract class SimpleEntityFactory<T> implements EntityFactory<T> {

    private static int nextId = 1;

    protected int getNextId(){
        return nextId+1;
    }

}
