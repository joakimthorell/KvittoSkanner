package corp.skaj.foretagskvitton.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.activities.ArchiveActivity;
import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;

public class ArchiveFragment extends Fragment {
    private TextView mPrice;
    private TextView mTax;
    private TextView mDate;
    private TextView mComment;
    private Spinner mSupplier;
    private Spinner mEmployees;
    private Spinner mCompany;
    private Spinner mCategory;

    public ArchiveFragment() {
        // Required empty public constructor
    }

    public static ArchiveFragment create() {
        ArchiveFragment fragment = new ArchiveFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String purchaseId = getArguments().getString(ArchiveActivity.ARCHIVE_BUNDLE);
        View view = inflater.inflate(R.layout.fragment_archive, container, false);
        setupFragment(view, purchaseId);
        return view;
    }

    private void setupFragment(View view, String purchaseId) {
        Purchase purchase = getCurrentPurchase(purchaseId);

        mDate = (TextView) view.findViewById(R.id.archive_receipt_date);
        mCompany = (Spinner) view.findViewById(R.id.archive_receipt_company);
        mSupplier = (Spinner) view.findViewById(R.id.archive_receipt_supplier);
        mCategory = (Spinner) view.findViewById(R.id.archive_receipt_categories);
        mPrice = (TextView) view.findViewById(R.id.archive_receipt_price);
        mTax = (TextView) view.findViewById(R.id.archive_receipt_moms);
        mEmployees = (Spinner) view.findViewById(R.id.archive_receipt_employee);
        mComment = (TextView) view.findViewById(R.id.archive_receipt_comment);

        //Category spinner
        ArrayAdapter<String> categoryAdapter = buildArrayAdapter(view, Category.getCategories());
        setArrayAdapter(categoryAdapter, mCategory);

        //Employee spinner
        ArrayAdapter<String> employeeAdapter = buildArrayAdapter(view, empolyesAsList());
        setArrayAdapter(employeeAdapter, mEmployees);

        //Supplier spinner
        ArrayAdapter<String> supplierAdapter = buildArrayAdapter(view, suppliersAsList(purchaseId));
        setArrayAdapter(supplierAdapter, mSupplier);

        //Company spinner
        ArrayAdapter<String> companyAdapter = buildArrayAdapter(view, companiesAsList());
        setArrayAdapter(companyAdapter, mCompany);

        mPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        mPrice.setText(String.valueOf(purchase.getReceipt().getTotal()) + "0");
        mTax.setText("Moms: " + String.valueOf(purchase.getReceipt().getProducts().get(0).getTax()) + " %");

        SimpleDateFormat dateRaw = new SimpleDateFormat("yyyy-MM-dd");
        String receiptDate = dateRaw.format(purchase.getReceipt().getDate().getTime());
        mDate.setText(receiptDate);
    }

    private ArrayAdapter<String> buildArrayAdapter(View view, List<String> list) {
        return new ArrayAdapter<>(view.getContext(), R.layout.support_simple_spinner_dropdown_item, list);
    }

    private void setArrayAdapter(ArrayAdapter<String> adapter, Spinner spinner) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private Purchase getCurrentPurchase(String purchaseId) {
        return getDataHandler().getPurchases(getUser()).getPurchase(purchaseId);
    }

    private User getUser() {
        return getDataHandler().readData(User.class.getName(), User.class);
    }

    private IData getDataHandler() {
        return (IData) getContext().getApplicationContext();
    }

    private List<String> empolyesAsList() {
        List<String> list = new ArrayList<>();
        List<Company> companies = getUser().getCompanies();
        for (Company c : companies) {
            for (Employee e : c.getEmployees()) {
                list.add(e.getName());
            }
        }
        return list;
    }

    private List<String> companiesAsList() {
        List<String> list = new ArrayList<>();
        List<Company> companies = getUser().getCompanies();
        for (Company c : companies) {
            list.add(c.getName());
        }
        return list;
    }

    private List<String> suppliersAsList(String purchaseId){
        List<String> list = new ArrayList<>();
        Purchase purchase = getCurrentPurchase(purchaseId);
        if (!isSupplierNull(purchase)) {
            List<Supplier> suppliers = getUser().getSuppliers();
            for (Supplier s : suppliers) {
                list.add(s.getName());
            }
        }
        return list;
    }

    private boolean isSupplierNull(Purchase purchase) {
        return purchase.getSupplier() == null;
    }

    /*
       // mDate TODO - Get calender pop-up for correct entries  
        Calendar newCalendar = Calendar.getInstance();  
        mDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener()
        {  
            @Override 
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) { 
                Calendar newDate = Calendar.getInstance(); 
                newDate.set(year, monthOfYear, dayOfMonth); 
                String formattedDate = DatePage.dateFormatter.format(newDate.getTime());  
                mDate.setText(formattedDate); 
                mPage.getData().putString(DatePage.SIMPLE_DATA_KEY, formattedDate); 
                mPage.notifyDataChanged(); 
        }  

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));  
     */

    public double getPrice() {
            return Double.valueOf(String.valueOf(mPrice.getText()));
        }

    public double getTax() {
        String newTax = String.valueOf((mTax.getText()));
        return Double.valueOf(newTax.substring(7, newTax.length() - 2));
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

    public String getSupplier(String purchaseId) {
        return !isSupplierNull(getCurrentPurchase(purchaseId)) ? mSupplier.getSelectedItem().toString() : null;
    }

    public String getComment() {
        return String.valueOf(mComment.getText());
    }

    public String getPurchaseType(){
        return null;
    }

    public String getEmployee (){
    return null;
    }
}