package corp.skaj.foretagskvitton.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import corp.skaj.foretagskvitton.R;

public class AddReceiptController {
    // these actions are for Archive FAB
    public static final String CAMERA_ACTION = "time_to_take_picture";
    public static final String GALLERY_ACTION = "collect_image_from_gallery";
    public static final String NO_IMAGE_ACTION = "there_should_be_no_image";

    private Context mContext;
    private FloatingActionsMenu mButton;

    private Drawable mCameraDraw;
    private Drawable mGalleryDraw; // todo fix image for coming buttons
    private Drawable mNoImageDraw;

    private Class<?> mArchiveNextActivity;
    private Class<?> mCompanyNextActivity;
    private Class<?> mSupplierNextActivity;

    public AddReceiptController(Context context,
                                Class<?> archiveNextActivity,
                                Class<?> companyNextActivity,
                                Class<?> supplierNextActivity) {
        mContext = context;

        mCameraDraw = context.getDrawable(R.drawable.ic_camera);
        mGalleryDraw = context.getDrawable(R.drawable.ic_folder); // Todo fix other icons here
        mNoImageDraw = context.getDrawable(R.drawable.ic_close);

        mArchiveNextActivity = archiveNextActivity;
        mCompanyNextActivity = companyNextActivity;
        mSupplierNextActivity = supplierNextActivity;

        try {
            mButton = (FloatingActionsMenu) ((Activity) context).findViewById(R.id.floating_action_button);
        } catch (ClassCastException cce) {
            System.out.println("FloatingActionButton is not created yet");
        }

    }

    public AddReceiptController setButton(FloatingActionsMenu button) {
        mButton = button;
        return this;
    }

    public AddReceiptController setListener(MainController.State state) {
        if (mButton == null) {
            System.out.println("FloatingButton is null in controller");
            return this;
        }

        if (state == MainController.State.COMPANY) {
            return setButtonForCompany();
        } else if (state == MainController.State.ARCHIVE) {
            return setButtonForArchive();
        } else if (state == MainController.State.SUPPLIER) {
            return setButtonForSupplier();
        }
        return this;
    }

    private AddReceiptController setButtonForArchive() {
        FloatingActionButton camera = new FloatingActionButton(mContext);
        camera.setTitle(mContext.getString(R.string.camera));
        camera.setImageDrawable(mCameraDraw);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, mArchiveNextActivity);
                intent.setAction(CAMERA_ACTION);
                mContext.startActivity(intent);
            }
        });
        mButton.addButton(camera);
        return this;
    }

    private AddReceiptController setButtonForCompany() {
        mButton.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                // TODO create-new-company fragment
                // This is a workaround to use the same menu for each fragment
                Toast.makeText(mContext, "Should go to Create new company page", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMenuCollapsed() {
                // nothing to do here!
            }
        });
        return this;
    }

    private AddReceiptController setButtonForSupplier() {
        mButton.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                Toast.makeText(mContext, "Should go to Create new Supplier page", Toast.LENGTH_SHORT).show();
                // TODO go to new activity
            }

            @Override
            public void onMenuCollapsed() {
                // nothing to do here!
            }
        });
        return this;
    }
}
