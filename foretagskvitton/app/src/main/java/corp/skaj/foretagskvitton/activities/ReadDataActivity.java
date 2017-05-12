package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.User;

public class ReadDataActivity extends AbstractActivity {
    public static final String KEY_FOR_IMAGE = "ReadDataActivity_Key_For_Image";
    public static final String BUILD_NEW_RECEIPT = "ReadDataActivity_build_receipt";
    private boolean openedFromOutside;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_application);
        Intent intent = getIntent();
        openedFromOutside = intent.getAction().equals(Intent.ACTION_SEND) && intent.getType().startsWith("image/");
        initData().start();
    }

    private Thread initData() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                readData();
                endLoadingBar();
            }
        });
    }

    private void readData() {
        try {
            ((User)readData(User.class.getName(), User.class)).getName();
        } catch (Exception e) {
            User newUser = new User("DEFAULT USER ");
            newUser.addCompany(new Company("DEFAULT COMPANY"));
            writeData(User.class.getName(), newUser);
        }
    }

    private void endLoadingBar() {
        if (openedFromOutside) {
            Uri URI = getIntent().getParcelableExtra(Intent.EXTRA_STREAM);
            Intent intent = new Intent(this, InitWizardActivity.class);
            intent.putExtra(KEY_FOR_IMAGE, URI);
            intent.setAction(BUILD_NEW_RECEIPT);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, AddNewPostActivity.class);
            startActivity(intent);
        }
    }
}
