package corp.skaj.foretagskvitton.activities;

import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Purchase;

public class ArchiveReceiptComments extends AppCompatActivity {
    private Purchase mPur = new Purchase(null, null);
    ArrayList<String> comments =new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_comments);

        String[] comments = getComments();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_archive_comments, comments);
        ListView listView = (ListView) findViewById(R.id.listOfComments);
        listView.setAdapter(adapter);
    }

    private String[] getComments(){
        int length = mPur.getComments().size();
        String[] commentes = new String[length];
        for(int i = 0; i < length; i++) {
            commentes[i] = mPur.getComments().get(i).getComment();
        }
        return commentes;
    }
}
