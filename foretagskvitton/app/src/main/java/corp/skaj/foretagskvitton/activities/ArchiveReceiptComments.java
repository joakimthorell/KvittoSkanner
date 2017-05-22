package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.PurchaseList;
import corp.skaj.foretagskvitton.model.User;

import static corp.skaj.foretagskvitton.activities.ArchiveActivity.COMMENT_ID;

public class ArchiveReceiptComments extends AppCompatActivity {
    Purchase mPurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        IData handler = (IData) getApplicationContext();
        User user = handler.readData(User.class.getName(), User.class);
        PurchaseList list = handler.getPurchases(user);

        String purchaseId= getIntent().getExtras().get(COMMENT_ID).toString();
        mPurchase = list.getPurchase(purchaseId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_comments);

        String[] comments = getComments();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.activity_archive_comments, comments);
        ListView listView = (ListView) findViewById(R.id.listOfComments);
        listView.setAdapter(adapter);
    }

    private String[] getComments(){
        int length = mPurchase.getComments().size();
        String[] commentes = new String[length];
        for(int i = 0; i < length; i++) {
            commentes[i] = mPurchase.getComments().get(i).getComment();
        }
        return commentes;
    }
}
