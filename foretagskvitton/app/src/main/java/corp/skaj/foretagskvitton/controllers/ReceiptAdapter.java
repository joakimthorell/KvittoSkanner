package corp.skaj.foretagskvitton.controllers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Receipt;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.MyViewHolder> {

    private List<Receipt> receiptList;
    private List<Company> comapanyList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public ReceiptAdapter(List<Receipt> receiptList, List<Company> companyList) {
        this.receiptList = receiptList;
        this.comapanyList = companyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.archive_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Receipt receipt = receiptList.get(position);
        Company company = comapanyList.get(position);

        //holder.title.setText(movie.getTitle());
        holder.title.setText(company.getName());

        //holder.genre.setText(movie.getGenre());
        holder.genre.setText(company.getName());

        //holder.year.setText(movie.getYear());
        holder.year.setText(String.valueOf(receipt.getDate()));

    }

    @Override
    public int getItemCount() {
        return receiptList.size();
    }
}