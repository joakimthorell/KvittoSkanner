package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;
import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.activities.singleReceipt;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.services.DataHandler;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.MyViewHolder> {

    public static final String ARCHIVE_KEY = "ArchiveKey";

    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }

    public ReceiptAdapter() {

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.archive_list_row, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Mabye it works", Toast.LENGTH_SHORT).show();
               Intent intent =  new Intent(context, singleReceipt.class);
                intent.putExtra(ARCHIVE_KEY, "lars");
                context.startActivity(intent);
            }
        });
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        IData dataHandler = (DataHandler)context.getApplicationContext();
        User user = dataHandler.readData(User.class.getName(), User.class);
        Purchase purchase = user.getCompany(new Company("DEFAULT COMPANY")).getEmployee("DEFAULT USER").getPurchases().get(position);

        //Purchase purchase = purchases.get(position);
        //System.out.println("AHAHAHHAHA OMG ROFLMAO" + purchase);

        Company company = user.getCompany(purchase);

        //holder.title.setText(movie.getTitle());
        holder.title.setText(company.getName());

        //holder.genre.setText(movie.getGenre());
        holder.genre.setText(purchase.getReceipt().getCategory().toString());

        //holder.year.setText(movie.getYear());
        SimpleDateFormat dateRaw = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateRaw.format(purchase.getReceipt().getDate().getTime());
        holder.year.setText(date);
    }

    @Override
    public int getItemCount() {
        IData dataHandler = (DataHandler)context.getApplicationContext();
        User user = dataHandler.readData(User.class.getName(), User.class);
        List<Purchase> purchases = user.getCompany(new Company("DEFAULT COMPANY")).getEmployee("DEFAULT USER").getPurchases();
        return purchases.size();
    }
}