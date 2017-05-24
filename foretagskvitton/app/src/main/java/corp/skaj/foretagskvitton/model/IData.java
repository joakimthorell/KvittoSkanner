package corp.skaj.foretagskvitton.model;

public interface IData {
    <T> void writeData(String key, T t);

    <T> T readData(String key, Class<T> classOfT);

    void removeData(String key);

    void clearData();

    void initDefaultUser();

    PurchaseList getPurchases(User user);

    /*

    User getUser();

    void saveUser();*/
}