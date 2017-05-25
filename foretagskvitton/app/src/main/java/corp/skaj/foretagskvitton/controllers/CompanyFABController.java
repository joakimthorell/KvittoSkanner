package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

public class CompanyFABController extends FABController {

    public CompanyFABController(Context context, Class<?> nextActivity) {
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
                Toast.makeText(getContext(), "Should go to Create new company page", Toast.LENGTH_SHORT).show();
                button.collapse();
            }

            @Override
            public void onMenuCollapsed() {

            }
        });
    }
}