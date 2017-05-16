package corp.skaj.foretagskvitton.model;

public interface IData {
    <T> void writeData(String key, T t);

    <T> T readData(String key, Class<T> classOfT);

    <T> void removeData(String key);
}