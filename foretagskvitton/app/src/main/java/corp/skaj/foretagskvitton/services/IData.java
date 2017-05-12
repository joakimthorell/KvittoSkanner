package corp.skaj.foretagskvitton.services;

public interface IData {
    <T> void writeData(String key, T t);
    Object readData(String key, Class c);
}
