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

    public static final int REQUEST_WIZARD = 9873;

    public static final String CAMERA_ACTION = "time_to_take_picture";
    public static final String GALLERY_ACTION = "collect_image_from_gallery";
    public static final String NO_IMAGE_ACTION = "there_should_be_no_image";

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
        FloatingActionButton camera = new FloatingActionButton(getContext());
        camera.setTitle(getContext().getString(R.string.camera));
        camera.setImageDrawable(mCameraDraw);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(CAMERA_ACTION);
                button.collapse();
            }
        });
        button.addButton(camera);

        FloatingActionButton gallery = new FloatingActionButton(getContext());
        gallery.setTitle(getContext().getString(R.string.gallery));
        gallery.setImageDrawable(mGalleryDraw);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(GALLERY_ACTION);
                button.collapse();
            }
        });
        button.addButton(gallery);

        FloatingActionButton noImage = new FloatingActionButton(getContext());
        noImage.setTitle(getContext().getString(R.string.no_image));
        noImage.setImageDrawable(mNoImageDraw);
        noImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(NO_IMAGE_ACTION);
                button.collapse();
            }
        });
        button.addButton(noImage);

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

    private Category getRandomCategory() {
        List<String> list = Category.getCategories();
        Random rand = new Random();
        Category c = Category.valueOf(list.get(rand.nextInt(list.size())));
        return c;
    }

    private void startNewActivity(String action) {
        mActivity.startNewActivityForResult(getNextActivity(), REQUEST_WIZARD, action, null, null);
    }
}
