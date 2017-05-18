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

public class ArchiveAdapter extends UltimateViewAdapter <ArchiveAdapter.SimpleAdapterViewHolder>{

    private PurchaseList mPurchases;
    private IData dataHandler;

    public ArchiveAdapter(IData dataHandler){
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.archive_list_item, parent, false);
        return new SimpleAdapterViewHolder(v);
    }

    @Override
    public int getAdapterItemCount() {
        return mPurchases.size();
    }

    @Override
    public long generateHeaderId(int position) {
        // If something wrong try -1 here!
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

        public TextView companyTitle, categoryTitle, dateTitle;

        public SimpleAdapterViewHolder(final View itemView) {
            super(itemView);

            companyTitle = (TextView) itemView.findViewById(R.id.title);
            categoryTitle = (TextView) itemView.findViewById(R.id.genre);
            dateTitle = (TextView) itemView.findViewById(R.id.year);
        }
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position) {

        User user = dataHandler.readData(User.class.getName(), User.class);
        Purchase pur = mPurchases.get(position);

        holder.companyTitle.setText(user.getCompany(pur).getName());
        holder.categoryTitle.setText(pur.getReceipt().getProducts().get(0).getCategory().name());

        SimpleDateFormat dateRaw = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateRaw.format(pur.getReceipt().getDate().getTime());
        holder.dateTitle.setText(date);
    }

}
