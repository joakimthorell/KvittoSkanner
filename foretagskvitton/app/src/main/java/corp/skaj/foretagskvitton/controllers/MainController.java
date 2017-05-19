package corp.skaj.foretagskvitton.controllers;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.ArchiveAdapter;
import corp.skaj.foretagskvitton.view.IAdapterController;
import corp.skaj.foretagskvitton.view.ListFragment;
import corp.skaj.foretagskvitton.view.SupplierAdapter;

public class MainController<T> implements IAdapterController<T> {
    private IMain mListener;
    private Context context;
    private IData dataHandler;


    public MainController(IMain listener, Context context, IData dataHandler) {
        mListener = listener;
        this.context = context;
        this.dataHandler = dataHandler;
    }

    @Override
    public void setListener(UltimateRecyclerviewViewHolder<T> view, final String data, final String key) {
    }

    public void initBottomBar () {
        final BottomBar bottomBar = (BottomBar) ((Activity)context).findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (bottomBar.getCurrentTabId() != tabId) {
                    switch (tabId) {
                        case R.id.action_business:
                            mListener.buildCompanyFragment();
                            return;
                        case R.id.action_archive:
                            mListener.buildArchiveFragment();
                            return;
                        case R.id.action_user:
                            //TODO
                            return;
                        default:
                            return;
                    }
                }
            }
        });
    }
}