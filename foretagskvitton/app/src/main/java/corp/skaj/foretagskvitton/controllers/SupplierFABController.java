package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import corp.skaj.foretagskvitton.view.ListFragment;

public class SupplierFABController extends FABController {

    public SupplierFABController(Context context, Class<?> nextActivity) {
        super(context, nextActivity);
    }

    @Override
    public void bindButton(FloatingActionsMenu button) {
        bindFAB(button);
    }

    private void bindFAB(final FloatingActionsMenu button) {
        button.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                Toast.makeText(getContext(), "Should go to Create new Supplier page", Toast.LENGTH_SHORT).show();
                button.collapse(); // test, remove if error
            }

            @Override
            public void onMenuCollapsed() {

            }
        });
    }
}
