package corp.skaj.foretagskvitton.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.text.SimpleDateFormat;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.PurchaseList;
import corp.skaj.foretagskvitton.model.User;

public class SupplierAdapter extends UltimateViewAdapter<SupplierAdapter.SimpleAdapterViewHolder> {
    private PurchaseList mPurchases;
    private IData dataHandler;

    public SupplierAdapter(IData dataHandler){
        this.dataHandler = dataHandler;
        this.mPurchases = dataHandler.getPurchases();
    }

    @Override
    public SimpleAdapterViewHolder newFooterHolder(View view) {
        return new SimpleAdapterViewHolder(view);
    }

    @Override
    public SimpleAdapterViewHolder newHeaderHolder(View view) {
        return new SimpleAdapterViewHolder(view);
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.supplier_list_item, parent, false);
        return new SimpleAdapterViewHolder(v);
    }

    @Override
    public int getAdapterItemCount() {
        return mPurchases.size();
    }

    @Override
    public long generateHeaderId(int position) {
        // If something wrong here try returning -1 here!
        return 0;
    }

    @Override
    public SimpleAdapterViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public class SimpleAdapterViewHolder extends UltimateRecyclerviewViewHolder {

        public TextView supplierTitle, dateTitle;

        public SimpleAdapterViewHolder(final View itemView) {
            super(itemView);

            supplierTitle = (TextView) itemView.findViewById(R.id.supplierTitle);
            dateTitle = (TextView) itemView.findViewById(R.id.supplierYear);
        }
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position) {

       User user = dataHandler.readData(User.class.getName(), User.class);
        Purchase pur = mPurchases.get(position);

        holder.supplierTitle.setText(checkForSupplier(position, user));

        SimpleDateFormat dateRaw = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateRaw.format(pur.getReceipt().getDate().getTime());
        holder.dateTitle.setText(date);
    }

    private String checkForSupplier(int position, User user){
        try {
           user.getSuppliers().get(position).getName();
        } catch(Exception e) {
            return "Supplier not specified";
        }
        return user.getSuppliers().get(position).getName();
    }

}