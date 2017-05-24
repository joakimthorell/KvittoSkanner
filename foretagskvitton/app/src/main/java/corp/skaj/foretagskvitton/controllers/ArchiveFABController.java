package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import corp.skaj.foretagskvitton.R;

public class ArchiveFABController extends FABController {

    public static final String CAMERA_ACTION = "time_to_take_picture";
    public static final String GALLERY_ACTION = "collect_image_from_gallery";
    public static final String NO_IMAGE_ACTION = "there_should_be_no_image";

    private Drawable mCameraDraw;
    private Drawable mGalleryDraw;
    private Drawable mNoImageDraw;

    public ArchiveFABController(Context context,
                                Class<?> nextActivity) {
        super(context, nextActivity);

        mCameraDraw = context.getDrawable(R.drawable.ic_camera);
        mGalleryDraw = context.getDrawable(R.drawable.ic_image);
        mNoImageDraw = context.getDrawable(R.drawable.ic_close);


    }

    @Override
    public void bindButton(FloatingActionsMenu button) {
        bindFAB(button);
    }

    private void bindFAB(FloatingActionsMenu button) {
        FloatingActionButton camera = new FloatingActionButton(getContext());
        camera.setTitle(getContext().getString(R.string.camera));
        camera.setImageDrawable(mCameraDraw);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), getNextActivity());
                intent.setAction(CAMERA_ACTION);
                getContext().startActivity(intent);
            }
        });
        button.addButton(camera);

        FloatingActionButton gallery = new FloatingActionButton(getContext());
        gallery.setTitle(getContext().getString(R.string.gallery));
        gallery.setImageDrawable(mGalleryDraw);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), getNextActivity());
                intent.setAction(GALLERY_ACTION);
                getContext().startActivity(intent);
            }
        });
        button.addButton(gallery);

    }
}
