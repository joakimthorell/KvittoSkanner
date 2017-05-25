package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.Intent;
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
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Product;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.Receipt;
import corp.skaj.foretagskvitton.view.ArchiveListFragment;

public class ArchiveListFABController extends FABController {

    public static final String CAMERA_ACTION = "time_to_take_picture";
    public static final String GALLERY_ACTION = "collect_image_from_gallery";
    public static final String NO_IMAGE_ACTION = "there_should_be_no_image";

    private Drawable mCameraDraw;
    private Drawable mGalleryDraw;
    private Drawable mNoImageDraw;

    public ArchiveListFABController(Context context,
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

        FloatingActionButton noImage = new FloatingActionButton(getContext());
        noImage.setTitle(getContext().getString(R.string.no_image));
        noImage.setImageDrawable(mNoImageDraw);
        noImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), getNextActivity());
                intent.setAction(NO_IMAGE_ACTION);
                getContext().startActivity(intent);
            }
        });
        button.addButton(noImage);

        FloatingActionButton demoButton = new FloatingActionButton(getContext());
        demoButton.setTitle("DEMO");
        demoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IData dataHandler = (IData) getContext().getApplicationContext();
                createNewReceipt();
                AppCompatActivity activity = (AppCompatActivity) getContext();
                ArchiveListFragment fragment = (ArchiveListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
                fragment.getAdapter().setNewData(dataHandler.getPurchases());
            }
        });
        button.addButton(demoButton);
    }


    private void createNewReceipt() {
        IData handler = (IData) getContext().getApplicationContext();
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
}
