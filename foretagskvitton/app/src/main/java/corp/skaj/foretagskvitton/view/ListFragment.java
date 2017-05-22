package corp.skaj.foretagskvitton.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import corp.skaj.foretagskvitton.R;

/**
 * A simple {@link Fragment} subclass.
 * This fragment is showing a {@link RecyclerView} including a
 * {@link FloatingActionsMenu} that can either be used as a direct button
 * or add more buttons into it.
 */
public class ListFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private Callback mObserver;
    private FloatingActionsMenu mButton;
    private boolean mAdapterIsComplete;

    // internal interface so not depending on anything else
    public interface Callback {
        void onListCreated();
    }


    public ListFragment() {
    }

    public static ListFragment create (RecyclerView.Adapter viewAdapater) {
        ListFragment fragment = new ListFragment()
                .setAdapterBoolean()
                .setAdapter(viewAdapater);
        return fragment;
    }

    public static ListFragment create(RecyclerView.Adapter viewAdapter, Callback observer) {
        ListFragment fragment = create(viewAdapter)
                .setListener(observer)
                .setAdapterBoolean();
        return fragment;
    }

    private ListFragment setAdapterBoolean() {
        mAdapterIsComplete = false;
        return this;
    }

    private ListFragment setAdapter (RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
        return this;
    }

    private ListFragment setListener(Callback observer) {
        mObserver = observer;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        return v;
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
            mButton = (FloatingActionsMenu) getActivity().findViewById(R.id.floating_action_button);

            DividerItemDecoration divider = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
            mRecyclerView.addItemDecoration(divider);

            if (mObserver != null) {
                System.out.println("ListFragment shouting to observer");
                mObserver.onListCreated();
            }
        }
    }

    public FloatingActionsMenu getButton() {
        return mButton;
    }

    public BaseQuickAdapter getAdapter() {
        return (BaseQuickAdapter)mAdapter;
    }
}