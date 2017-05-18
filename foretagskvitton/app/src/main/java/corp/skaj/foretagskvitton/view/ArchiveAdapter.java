package corp.skaj.foretagskvitton.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.PurchaseList;

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

    }

}
