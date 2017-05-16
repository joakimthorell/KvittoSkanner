package corp.skaj.foretagskvitton.controllers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.User;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.MyViewHolder> {
    public static final String ARCHIVE_KEY = "ArchiveKey";
    private User mUser;

    public ReceiptAdapter(User user) {
        mUser = user;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.archive_list_item, parent, false);
        /*
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Mabye it works", Toast.LENGTH_SHORT).show();
               Intent intent =  new Intent(context, singleReceipt.class);
                intent.putExtra(ARCHIVE_KEY, "lars");
                context.startActivity(intent);
            }
        });
        */
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Purchase purchase = mUser.getCompanies().get(0).getEmployees().get(0).getPurchases().get(position);
        Company company = mUser.getCompany(purchase);

        holder.title.setText(company.getName());
        holder.genre.setText(purchase.getReceipt().getProducts().get(0).getCategory().name());

        SimpleDateFormat dateRaw = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateRaw.format(purchase.getReceipt().getDate().getTime());
        holder.year.setText(date);
    }

    @Override
    public int getItemCount() {
        List<Purchase> purchases = mUser.getCompanies().get(0).getEmployees().get(0).getPurchases();
        return purchases.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }
}