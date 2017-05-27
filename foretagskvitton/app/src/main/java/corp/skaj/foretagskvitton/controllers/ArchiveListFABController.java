package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.IDataHandler;
import corp.skaj.foretagskvitton.model.Product;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.Receipt;
import corp.skaj.foretagskvitton.view.ArchiveListFragment;

public class ArchiveListFABController extends AbstractFABController {
    public static final String CAMERA_ACTION = "time_to_take_picture";
    public static final String GALLERY_ACTION = "collect_image_from_gallery";
    public static final String NO_IMAGE_ACTION = "there_should_be_no_image";
    public static final int REQUEST_WIZARD = 9873;
    private Drawable mCameraDraw;
    private Drawable mGalleryDraw;
    private Drawable mNoImageDraw;
    private IActivity mActivity;

    public ArchiveListFABController(Context context,
                                    Class<?> nextActivity,
                                    IActivity activity) {
        super(context, nextActivity);
        mActivity = activity;
        mCameraDraw = context.getDrawable(R.drawable.ic_camera);
        mGalleryDraw = context.getDrawable(R.drawable.ic_image);
        mNoImageDraw = context.getDrawable(R.drawable.ic_close);
    }

    @Override
    public void bindButton(FloatingActionsMenu button) {
        bindFAB(button);
    }

    private void bindFAB(final FloatingActionsMenu button) {
        FloatingActionButton camera = buildFAB();
        camera.setTitle(getContext().getString(R.string.camera));
        camera.setImageDrawable(mCameraDraw);
        setOnClickListener(camera, button, CAMERA_ACTION);
        button.addButton(camera);

        FloatingActionButton gallery = buildFAB();
        gallery.setTitle(getContext().getString(R.string.gallery));
        gallery.setImageDrawable(mGalleryDraw);
        setOnClickListener(gallery, button, GALLERY_ACTION);
        button.addButton(gallery);

        FloatingActionButton noImage = buildFAB();
        noImage.setTitle(getContext().getString(R.string.no_image));
        noImage.setImageDrawable(mNoImageDraw);
        setOnClickListener(noImage, button, NO_IMAGE_ACTION);
        button.addButton(noImage);

        setDemoButton(button);
    }

    private FloatingActionButton buildFAB() {
        return new FloatingActionButton(getContext());
    }

    private void setOnClickListener(FloatingActionButton camera, final FloatingActionsMenu button,
                                    final String activityToStart) {
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(activityToStart);
                button.collapse();
            }
        });
    }

    private Category getRandomCategory() {
        List<String> list = Category.getCategories();
        Random rand = new Random();
        Category c = Category.valueOf(list.get(rand.nextInt(list.size())));
        return c;
    }

    private void startNewActivity(String action) {
        mActivity.startNewActivityForResult(getNextActivity(), REQUEST_WIZARD, action, null, null);
    }

    // Below is temporary for testing... Adds random receipt in archive list.
    private void createNewReceipt() {
        IDataHandler handler = (IDataHandler) getContext().getApplicationContext();
        Random rand = new Random();
        double total = rand.nextInt(999) + 1;
        Product p = new Product(Product.ALL_PRODUCTS, getRandomCategory(), total, 25);
        Calendar c = Calendar.getInstance();
        Receipt r = new Receipt(p, c, total, null);
        Purchase pr = new Purchase(r, Purchase.PurchaseType.PRIVATE);
        handler.getUser().getCompanies().get(0).getEmployees().get(0).addPurchase(pr);
        handler.saveUser();
    }

    private void setDemoButton(FloatingActionsMenu button) {
        FloatingActionButton demoButton = new FloatingActionButton(getContext());
        demoButton.setTitle("DEMO");
        demoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IDataHandler dataHandler = (IDataHandler) getContext().getApplicationContext();
                createNewReceipt();
                AppCompatActivity activity = (AppCompatActivity) getContext();
                ArchiveListFragment fragment = (ArchiveListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
                fragment.getAdapter().setNewData(dataHandler.getPurchases());
            }
        });
        button.addButton(demoButton);
    }
}
