package corp.skaj.foretagskvitton.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;

public class CompanyAdapter extends UltimateViewAdapter <CompanyAdapter.SimpleAdapterViewHolder> {

    public static final String COMPANY_KEY = "CompanyKey";
    private List<Company> mCompanies;
    private IAdapterController mController;

    public CompanyAdapter (List<Company> companies, IAdapterController controller) {
        this.mCompanies = companies;
        mController = controller;
    }
    @Override
    public SimpleAdapterViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public SimpleAdapterViewHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_list_view, parent, false);
        SimpleAdapterViewHolder simpleAdapterViewHolder = new SimpleAdapterViewHolder(view);
        return simpleAdapterViewHolder;
    }

    @Override
    public int getAdapterItemCount() {
        return mCompanies.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position) {

        String name = mCompanies.get(position).getName();
        holder.textView.setText(name);

        mController.setListener(holder, name, COMPANY_KEY);

    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public class SimpleAdapterViewHolder extends UltimateRecyclerviewViewHolder {

        public TextView textView;

        public SimpleAdapterViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.company_textview);
        }


    }



}
