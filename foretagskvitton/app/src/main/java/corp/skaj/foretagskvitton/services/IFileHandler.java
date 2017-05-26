package corp.skaj.foretagskvitton.services;

import android.net.Uri;

import java.io.File;

public interface IFileHandler {
    void updateImageAddress(String newAddress);
    void onReadResult(Uri addressToNewFile);
    void initProgressBar();
    File getExternalFileDir();
}