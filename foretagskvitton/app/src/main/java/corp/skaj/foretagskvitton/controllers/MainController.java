package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.ArchiveAdapter;
import corp.skaj.foretagskvitton.view.CompanyAdapter;
import corp.skaj.foretagskvitton.view.MultiDialog;
import corp.skaj.foretagskvitton.view.SupplierAdapter;
import corp.skaj.foretagskvitton.view.SupplierListFragment;

public class MainController implements MultiDialog.Callback {

    private IActivity mListener;
    private Context mContext;
    private BottomBar mBottombar;


    public MainController(IActivity listener, Context context) {
        mListener = listener;
        mContext = context;

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
                mListener.nextActivity(nextActivity, key, data);
            }
        });
    }

    public void setSupplierAdapterListener(final SupplierAdapter sAdapter, final String key) {
        sAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String data = sAdapter.getData().get(position).getName();
                showEditSupplierDialog(data);
            }
        });
    }

    public void initBottomBar (final BottomBar bottomBar) {
        mBottombar = bottomBar;
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (bottomBar.getCurrentTabId() != tabId) {
                    switch (tabId) {
                        case R.id.action_business:
                            mListener.buildCompanyFragment();
                            return;
                        case R.id.action_archive:
                            mListener.buildArchiveListFragment();
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

    public void showEditSupplierDialog(String data) {
        IData handler = getHandler();
        User user = handler.getUser();
        Supplier supplier = user.getSupplier(data);

        new MultiDialog(mContext,
                this,
                MultiDialog.Type.EDITOR,
                supplier.getName(),
                mContext.getString(R.string.text_supplier))
        .newDialog().show();
    }

    public boolean setSelectedTab(int actionId) {
        if (mBottombar == null) {
            return false;
        }
        try {
            mBottombar.setDefaultTab(actionId);
            return true;
        } catch (Exception e) {
            System.out.println("Not able to set BottomBar default tab, need to be correct R.id");
        }

        return false;
    }

    @Override
    public void dialogData(String newData, String oldData, Bundle extras) {
        if (newData != null && newData.length() > 0) {
            IData handler = getHandler();
            Supplier s = handler.getUser().getSupplier(oldData);
            s.setName(newData);

            handler.saveUser();
            List<Supplier> suppliers = handler.getUser().getSuppliers();
            AppCompatActivity activity = (AppCompatActivity) mContext;
            SupplierListFragment fragment = (SupplierListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
            fragment.getAdapter().setNewData(suppliers);

            Toast.makeText(mContext, mContext.getString(R.string.main_controller_change_to) + " " + s.getName(), Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(mContext, mContext.getString(R.string.text_not_saved), Toast.LENGTH_SHORT).show();
    }

    private IData getHandler() {
        return ((IData) mContext.getApplicationContext());
    }
}
