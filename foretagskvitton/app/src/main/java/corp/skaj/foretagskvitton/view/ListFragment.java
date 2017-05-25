package corp.skaj.foretagskvitton.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
public abstract class ListFragment extends AbstractFragment {
    private RecyclerView mRecyclerView;
    private ILinkFABListener mObserver;
    private FloatingActionsMenu mButton;

    protected ListFragment() {
    }

    protected ListFragment setListener(ILinkFABListener observer) {
        mObserver = observer;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
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
            mRecyclerView.setAdapter(getBaseAdapter());
            mButton = (FloatingActionsMenu) getActivity().findViewById(R.id.floating_action_button);

            DividerItemDecoration divider = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
            mRecyclerView.addItemDecoration(divider);

            Toolbar toolbar = (Toolbar) view.findViewById(R.id.list_toolbar);
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            activity.setSupportActionBar(toolbar);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(true);


            if (mObserver != null) {
                mObserver.bindButton(mButton);
            }
        }
    }

    public FloatingActionsMenu getButton() {
        return mButton;
    }

    protected abstract BaseQuickAdapter getBaseAdapter();
}