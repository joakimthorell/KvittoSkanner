package corp.skaj.foretagskvitton.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
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
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.PurchaseList;
import corp.skaj.foretagskvitton.model.User;

public class ArchiveFragment extends Fragment {
    private TextView price;
    private TextView tax;
    private TextView date;
    private TextView supplier;
    private TextView comment;
    private TextView purchaseType;
    private Spinner employes;
    private Spinner company;
    private Spinner category;
    private Purchase mPur;


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
        tax = (TextView) view.findViewById(R.id.archive_receipt_moms);
        date = (TextView) view.findViewById(R.id.archive_receipt_date);
        supplier = (Spinner) view.findViewById(R.id.archive_receipt_supplier);
        comment = (TextView) view.findViewById(R.id.archive_receipt_comment);
        category = (Spinner) view.findViewById(R.id.archive_receipt_categories);
        company = (Spinner) view.findViewById(R.id.archive_receipt_company);
        employes = (Spinner) view.findViewById(R.id.archive_receipt_company);

        price.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), R.layout.support_simple_spinner_dropdown_item, Category.getCategories());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                user.getCompanies().get(0).getEmployees()); 
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
        company.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                user.getCompanies().get(0).getEmployees()); 
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
        company.setAdapter(arrayAdapter);


        ArrayAdapter<String> employeeAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                );
        employeeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        company.setAdapter(employeeAdapter);


        price.setText(String.valueOf(purchase.getReceipt().getTotal()) + "0");
        tax.setText("Moms: " + String.valueOf(purchase.getReceipt().getProducts().get(0).getTax()) + " %");
        supplier.setText(checkSupplier());

        SimpleDateFormat dateRaw = new SimpleDateFormat("yyyy-MM-dd");
        String receiptDate = dateRaw.format(purchase.getReceipt().getDate().getTime());
        date.setText(receiptDate);
    }
    /*
       // date TODO - Get calender pop-up for correct entries  
        Calendar newCalendar = Calendar.getInstance();  
        mDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener()
        {  
            @Override 
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) { 
                Calendar newDate = Calendar.getInstance(); 
                newDate.set(year, monthOfYear, dayOfMonth); 
                String formattedDate = DatePage.dateFormatter.format(newDate.getTime());  
                date.setText(formattedDate); 
                mPage.getData().putString(DatePage.SIMPLE_DATA_KEY, formattedDate); 
                mPage.notifyDataChanged(); 
        }  

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));  
     */

    private String checkSupplier() {
        try {
            mPur.getSupplier().getName();
        } catch (NullPointerException e) {
            return "Supplier not specified";
        }
        return mPur.getSupplier().getName();
    }

    public void sendSavedData () {


    }

    public double getCost () {
            return Double.valueOf(String.valueOf(price.getText()));
        }

    public double getTax() {
        String newTax = String.valueOf((tax.getText()));
        return Double.valueOf(newTax.substring(7, newTax.length() - 2));
    }

    public String getCategory() {
        return category.getSelectedItem().toString();
    }

    public String getCompany() {
        return company.getSelectedItem().toString();
    }

    public String getDate() {
        return String.valueOf(date.getText());
    }

    public String getSupplier() {
        return String.valueOf(supplier.getText());
    }

    public String getComment() {
        return String.valueOf(comment.getText());
    }

    public String getPurchaseType(){
        return null;
    }

    public String getEmployee (){
    return null;
    }

}


