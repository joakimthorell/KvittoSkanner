package corp.skaj.foretagskvitton.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import corp.skaj.foretagskvitton.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment create (RecyclerView.Adapter viewAdapter) {
        ListFragment fragment = new ListFragment();
        fragment.setAdapter(viewAdapter);
        return fragment;
    }

    private void setAdapter (RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {

            mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
            LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setSaveEnabled(false);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
