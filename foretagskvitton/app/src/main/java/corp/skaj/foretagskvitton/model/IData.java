package corp.skaj.foretagskvitton.model;

public interface IData {
    <T> void writeData(String key, T t);
    Object readData(String key, Class c);
}