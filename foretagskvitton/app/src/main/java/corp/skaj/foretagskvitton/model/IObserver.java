package corp.skaj.foretagskvitton.model;

/**
 * Observer interface allowing data to be saved when a new receipt is added.
 */
public interface IObserver {
    void collectData();
}