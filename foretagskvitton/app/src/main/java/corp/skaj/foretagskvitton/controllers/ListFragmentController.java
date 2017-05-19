package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialcab.MaterialCab;

public class ListFragmentController implements MaterialCab.Callback {
    private MaterialCab mMaterialCab;

    public ListFragmentController(Context context) {
    }

    @Override
    public boolean onCabCreated(MaterialCab cab, Menu menu) {
        return false;
    }

    @Override
    public boolean onCabItemClicked(MenuItem item) {
        return false;
    }

    @Override
    public boolean onCabFinished(MaterialCab cab) {
        return false;
    }
}
