package corp.skaj.foretagskvitton.controllers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.Receipt;
import corp.skaj.foretagskvitton.model.User;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.MyViewHolder> {

    private List<Purchase> purchases;
    private List<Company> comapanyList;
    private User user;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }

    public ReceiptAdapter(List<Purchase> purchases, User user) {
        this.purchases = purchases;
        this.comapanyList = user.getCompanies();
        this.user = user;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.archive_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Purchase purchase = purchases.get(position);
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
        return purchases.size();
    }
}