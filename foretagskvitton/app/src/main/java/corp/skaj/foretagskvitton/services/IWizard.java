package corp.skaj.foretagskvitton.services;

import android.net.Uri;

import java.io.File;

public interface IWizard {
    void updateImageAddress(String newAddress);
    void startWizard(Uri addressToNewFile);
    void initProgressBar();
    File getExternalFileDir();
}