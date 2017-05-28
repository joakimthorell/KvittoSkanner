package corp.skaj.foretagskvitton.controllers;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.Comment;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IDataHandler;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.AbstractFragment;
import corp.skaj.foretagskvitton.view.IImage;
import corp.skaj.foretagskvitton.view.IReceipt;
import corp.skaj.foretagskvitton.view.ReceiptFragment;

public class ReceiptController implements IReceipt {
    private ReceiptFragment fragment;
    private String purchaseId;
    private IDataHandler dataHandler;
    private IImage mListener;
    private DatePickerDialog mDateDialog;

    public ReceiptController(IDataHandler dataHandler, String purId, ReceiptFragment fragment, IImage listener) {
        this.dataHandler = dataHandler;
        purchaseId = purId;
        this.fragment = fragment;
        mListener = listener;
    }

    public void updateReceiptData() {
        User user = dataHandler.getUser();
        Purchase purchase = dataHandler.getPurchases().getPurchase(purchaseId);
        // price 
        purchase.getReceipt().setTotal(fragment.getPrice());
        // category 
        purchase.getReceipt().getProducts().get(0).setCategory(Category.valueOf(fragment.getCategory().toUpperCase()));
        // vat 
        double vat = fragment.getVat();
        purchase.getReceipt().getProducts().get(0).setTax(vat);
        fragment.setVatView(vat);
        // supplier
        Supplier updatedSupplier = user.getSupplier(fragment.getSupplier());
        purchase.setSupplier(updatedSupplier);
        // payment method 
        //purchase.setPurchaseType(selectCorrectPurchase());
        // company 
        Company updatedCompany = user.getCompany(fragment.getCompany());
        //comments
        updateComment(purchase);

        Employee oldPurchaseOwner = user.getCompany(purchase).getEmployee(purchase);
        Employee newPurchaseOwner = updatedCompany.getEmployee(fragment.getEmployee());

        newPurchaseOwner.addPurchase(purchase);
        oldPurchaseOwner.removePurchase(purchase);

        dataHandler.saveUser();
    }

    private void updateComment(Purchase purchase) {
        if (purchase.getComments().size() < 1) {
            if (fragment.getComment().length() > 0) {
                Comment c = new Comment(fragment.getComment());
                purchase.addComment(c);
            }
        } else {
            purchase.getComments().get(0).setComment(fragment.getComment());
        }
    }

    private Purchase.PurchaseType selectCorrectPurchase() {
        return fragment.getPurchaseType().equals("Företagskort") ? Purchase.PurchaseType.COMPANY : Purchase.PurchaseType.PRIVATE;
    }

    @Override
    public void bindButton(final FloatingActionsMenu button) {
        button.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                updateReceiptData();
                button.collapse();
                Toast.makeText(fragment.getContext(), fragment.getContext().getString(R.string.archive_save), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMenuCollapsed() {

            }
        });
    }

    @Override
    public void bindImage(ImageView clickableImage, final Uri URI) {
        clickableImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.setImagePressed(URI);
            }
        });
    }

    @Override
    public void bindSpinner(final Spinner spinnerToBind, final AbstractFragment af, final Spinner changingSpinner) {
        spinnerToBind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Company c = dataHandler.getUser().getCompany(spinnerToBind.getAdapter().getItem(position).toString());
                List<String> employeeNames = new ArrayList<String>();

                for (Employee e : c.getEmployees()) {
                    employeeNames.add(e.getName());
                }
                ArrayAdapter<String> employeeAdapter = af.buildArrayAdapter(employeeNames);
                af.setArrayAdapter(employeeAdapter, changingSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Empty on purpose
            }
        });
    }

    @Override
    public void bindDate(TextView textView, Context context) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDateDialog.show();
            }
        });
        String oldDate = String.valueOf(textView.getText());
        createDatePicker(oldDate, context, textView);
    }

    private void createDatePicker(String dateAsString, Context context, final TextView textView) {
        Calendar date = getCalendarFromString(dateAsString);
        mDateDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                String formattedDate = dateFormatter.format(newDate.getTime());
                textView.setText(formattedDate);
            }
        }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE));
    }

    private Calendar getCalendarFromString(String dateAsString) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateAsString);
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;
        }

        Calendar c = new GregorianCalendar();
        c.setTime(date);

        return c;
    }

}