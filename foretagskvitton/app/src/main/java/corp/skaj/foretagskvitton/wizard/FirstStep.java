package corp.skaj.foretagskvitton.wizard;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.activities.WizardActivity;

public class FirstStep extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_step);

        setupActionBar();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Välj kort");
        actionBar.setSubtitle("1 av 12");
        // https://developer.android.com/training/appbar/setting-up.html
        // Borde användas istället, sparar länken här
    }

    public void buttonPressedOFC(View view) {
        Intent intent = new Intent(this, WizardActivity.class);
        startActivity(intent);
    }
}
