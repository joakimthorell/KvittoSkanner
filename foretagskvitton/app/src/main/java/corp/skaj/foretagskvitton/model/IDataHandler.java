package corp.skaj.foretagskvitton.model;

/**
 * Interface class allowing user to save model data locally.
 */
public interface IDataHandler {
    String IMAGE_URI_KEY = "uri_for_image_in_wizard";
    String COLLECTED_STRINGS_KEY = "key_for_collected_strings";

    <T> void writeData(String key, T t);

    <T> T readData(String key, Class<T> classOfT);

    void removeData(String key);

    void clearData();

    boolean initDefaultUser();

    PurchaseList getPurchases();

    User getUser();

    boolean saveUser();
}