package corp.skaj.foretagskvitton.view;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.services.ReceiptScanner;

public class ReceiptFragment extends AbstractFragment {
    public static final String ARCHIVE_BUNDLE = "PURCHASE_ID";
    private FABCallback mFabListener;
    private ArchiveListener mImageListener;
    private TextView mPrice;
    private TextView mTax;
    private TextView mDate;
    private TextView mComment;
    private TextView mPurchaseType;
    private Spinner mSupplier;
    private Spinner mEmployees;
    private Spinner mCompany;
    private Spinner mCategory;
    private Purchase mPurchase;
    private ImageView mMiniImage;

    public ReceiptFragment() {
        // Required empty public constructor
    }

    public static ReceiptFragment create(String purchaseID) {
        Bundle b = new Bundle();
        b.putString(ARCHIVE_BUNDLE, purchaseID);
        ReceiptFragment fragment = new ReceiptFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String purchaseId = getArguments().getString(ARCHIVE_BUNDLE);
        View view = inflater.inflate(R.layout.fragment_archive, container, false);
        setupFragment(view, purchaseId);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        FloatingActionsMenu button = (FloatingActionsMenu) view.findViewById(R.id.archive_receipt_savebutton);
        mFabListener.bindButton(button);
        onClick();

    }

    public void setImageListener(ArchiveListener listener) {
        mImageListener = listener;
    }

    public void setFabListener(FABCallback listener) {
        mFabListener = listener;
    }

    private void setupFragment(View view, String purchaseId) {
        mPurchase = getCurrentPurchase(purchaseId);
        User user = getUser();
        setDateTextView(view, mPurchase);
        setPriceTextView(view, mPurchase);

        mEmployees = (Spinner) view.findViewById(R.id.archive_receipt_employee);
        mCompany = (Spinner) view.findViewById(R.id.archive_receipt_company);
        mSupplier = (Spinner) view.findViewById(R.id.archive_receipt_supplier);
        mCategory = (Spinner) view.findViewById(R.id.archive_receipt_categories);
        mTax = (TextView) view.findViewById(R.id.archive_receipt_moms);
        mComment = (TextView) view.findViewById(R.id.archive_receipt_comment);
        mPurchaseType = (TextView) view.findViewById(R.id.archive_receipt_purchaseType);
        mMiniImage = (ImageView) view.findViewById(R.id.miniature_image_receipt);

        mTax.setSingleLine();
        mComment.setSingleLine();
        mPrice.setSingleLine();

        //Category spinner
        ArrayAdapter<String> categoryAdapter = buildArrayAdapter(Category.getCategories());
        setArrayAdapter(categoryAdapter, mCategory);

        String selectedCat = mPurchase.getReceipt().getProducts().get(0).getCategory().name();
        int positionCat = categoryAdapter.getPosition(selectedCat);
        mCategory.setSelection(positionCat);

        //Company spinner
        ArrayAdapter<String> companyAdapter = buildArrayAdapter(getCompanies());
        setArrayAdapter(companyAdapter, mCompany);

        Company c = user.getCompany(mPurchase);

        String selectedCom = user.getCompany(mPurchase).getName();
        int positionCom = companyAdapter.getPosition(selectedCom);
        mCompany.setSelection(positionCom);

        //Employee spinner
        ArrayAdapter<String> employeeAdapter = buildArrayAdapter(getEmployees(null, mPurchase));
        setArrayAdapter(employeeAdapter, mEmployees);
        
        Employee e = c.getEmployee(mPurchase);
        String selectedEmp = e.getName();
        int positionEmp = employeeAdapter.getPosition(selectedEmp);
        mEmployees.setSelection(positionEmp);

        //Supplier spinner
        ArrayAdapter<String> supplierAdapter = buildArrayAdapter(getSuppliers());
        setArrayAdapter(supplierAdapter, mSupplier);
        mSupplier.setSelection(mPurchase.getSupplier() == null ? getSuppliers().size() - 1 :
                getSuppliers().indexOf(mPurchase.getSupplier()));


        mTax.setText(String.valueOf(mPurchase.getReceipt().getProducts().get(0).getTax()) + " %");

        mPurchaseType.setText(String.valueOf(mPurchase.getPurchaseType().name()));

        mComment.setText(mPurchase.getComments().size() > 0 ? mPurchase.getComments().get(0).getComment() : null);

        try {
            Bitmap bmp = ReceiptScanner.createImageFromURI(getContext(), Uri.parse(mPurchase.getReceipt().getPictureAdress()));
            mMiniImage.setImageBitmap(bmp);
            mMiniImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mImageListener.setImagePressed(Uri.parse(mPurchase.getReceipt().getPictureAdress()));
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
            mMiniImage.setImageDrawable(getContext().getDrawable(R.drawable.comingsoon));
        }
    }


    public void onClick() {
        mCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Company c = getUser().getCompany(mCompany.getAdapter().getItem(position).toString());
                List<String> employeeNames = new ArrayList<String>();

                for (Employee e : c.getEmployees()) {
                    employeeNames.add(e.getName());
                }

                ArrayAdapter<String> employeeAdapter = buildArrayAdapter(employeeNames);
                setArrayAdapter(employeeAdapter, mEmployees);

                //If we want a standard user, fix code below
                //Employee e = getUser().getCompanies().get(position).getEmployee(mPurchase);
                //mEmployees.setSelection(getEmployees().indexOf(e));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            //Empty on purpose
            }
        });
    }

    private void setPriceTextView(View view, Purchase purchase) {
        mPrice = (TextView) view.findViewById(R.id.archive_receipt_price);
        mPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        mPrice.setText(String.valueOf(purchase.getReceipt().getTotal()) + "0");
    }

    private void setDateTextView(View view, Purchase purchase) {
        mDate = (TextView) view.findViewById(R.id.archive_receipt_date);
        SimpleDateFormat dateRaw = new SimpleDateFormat("yyyy-MM-dd");
        String receiptDate = dateRaw.format(purchase.getReceipt().getDate().getTime());
        mDate.setText(receiptDate);
    }

    private Purchase getCurrentPurchase(String purchaseId) {
        return getDataHandler().getPurchases().getPurchase(purchaseId);
    }

    private List<String> getCompanies() {
        List<String> list = new ArrayList<>();
        List<Company> companies = getUser().getCompanies();
        for (Company c : companies) {
            list.add(c.getName());
        }
        return list;
    }

    private List<String> getSuppliers() {
        List<String> suppliersNames = new ArrayList<>();
        for (Supplier s : getUser().getSuppliers()) {
            suppliersNames.add(s.getName());
        }
        suppliersNames.add("Ingen grossist");
        return suppliersNames;
    }

    public double getPrice() {
        return Double.valueOf(String.valueOf(mPrice.getText()));
    }

    public double getTax() {
        String newTax = String.valueOf((mTax.getText())).substring(0, mTax.getText().length() - 2);
        return Double.valueOf(newTax);
    }

    public String getCategory() {
        return mCategory.getSelectedItem().toString();
    }

    public String getCompany() {
        return mCompany.getSelectedItem().toString();
    }

    public String getDate() {
        return mDate.getText().toString();
    }

    public String getSupplier() {
        return mSupplier.getSelectedItem().toString().equals("Ingen grossist") ? null :
                mSupplier.getSelectedItem().toString();
    }

    public String getComment() {
        return String.valueOf(mComment.getText());
    }

    public String getPurchaseType() {
        return mPurchaseType.getText().toString();
    }

    public String getEmployee() {
        return mEmployees.getSelectedItem().toString();
    }
}