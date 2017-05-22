package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.view.ArchiveAdapter;
import corp.skaj.foretagskvitton.view.CompanyAdapter;
import corp.skaj.foretagskvitton.view.SupplierAdapter;

public class MainController {
    public enum State {
        COMPANY,
        ARCHIVE,
        SUPPLIER
    }

    private IView mListener;


    public MainController(Context context) {
        mListener = (IView) context;
    }

    public void setArchiveAdapterListener(final ArchiveAdapter sAdapter, final Class<?> nextActivity, final String key) {
        sAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String data = sAdapter.getData().get(position).getId();
                mListener.nextActivity(nextActivity, key, data);
            }
        });
    }

    public void setCompanyAdapterListener(final CompanyAdapter sAdapter, final Class<?> nextActivity, final String key) {
        sAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String data = sAdapter.getData().get(position).getName();
                mListener.buildCompanyInfoFragment();
            }
        });
    }

    public void setSupplierAdapterListener(final SupplierAdapter sAdapter, final Class<?> nextActivity, final String key) {
        sAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String data = sAdapter.getData().get(position).getName();
                mListener.nextActivity(nextActivity, key, data);
            }
        });
    }

    public void initBottomBar (final BottomBar bottomBar) {
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
                        case R.id.action_supplier:
                            mListener.buildSupplierFragment();
                            return;
                        default:
                            return;
                    }
                }
            }
        });
    }
}
