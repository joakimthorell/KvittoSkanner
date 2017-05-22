package corp.skaj.foretagskvitton.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.activities.ArchiveActivity;
import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.PurchaseList;
import corp.skaj.foretagskvitton.model.User;

public class ArchiveFragment extends Fragment {
    private TextView price;
    private TextView moms;
    private TextView date;
    private TextView supplier;
    private TextView comment;
    private TextView company;
    private Spinner category;

    public ArchiveFragment() {
        // Required empty public constructor
    }

    public static ArchiveFragment create() {
        ArchiveFragment fragment = new ArchiveFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String purchaseId = getArguments().getString(ArchiveActivity.ARCHIVE_BUNDLE);
        View view = inflater.inflate(R.layout.fragment_archive, container, false);
        setupFragment(view, purchaseId);
        return view;
    }

    private void setupFragment(View view, String purchaseId) {
        IData dataHandler = (IData) getContext().getApplicationContext();
        User user = dataHandler.readData(User.class.getName(), User.class);
        PurchaseList purchases = dataHandler.getPurchases(user);
        Purchase purchase = purchases.getPurchase(purchaseId);

        price = (TextView) view.findViewById(R.id.archive_receipt_price);
        //price.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        //category = (TextView) view().findViewById(R.id.Category);
        category = (Spinner) view.findViewById(R.id.archive_receipt_categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), R.layout.support_simple_spinner_dropdown_item, Category.getCategories());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        moms = (TextView) view.findViewById(R.id.archive_receipt_moms);
        date = (TextView) view.findViewById(R.id.archive_receipt_date);
        supplier = (TextView) view.findViewById(R.id.archive_receipt_supplier);
        comment = (TextView) view.findViewById(R.id.archive_receipt_comment);
        company = (TextView) view.findViewById(R.id.archive_receipt_company);
        price.setText(String.valueOf(purchase.getReceipt().getTotal()) + "0");
        //category.set(purchase.getReceipt().getProducts().get(0).getCategory().name());
        moms.setText("Moms: " + String.valueOf(purchase.getReceipt().getProducts().get(0).getTax()) + " %");
        //supplier.setText(checkSupplier());
        comment.setText("\"" + purchase.getComments().get(0).getComment() + "\"");
        company.setText(user.getCompany(purchase).getName());
        SimpleDateFormat dateRaw = new SimpleDateFormat("yyyy-MM-dd");
        String receiptDate = dateRaw.format(purchase.getReceipt().getDate().getTime());
        date.setText(receiptDate);
    }
}
