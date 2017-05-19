package corp.skaj.foretagskvitton.controllers;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import corp.skaj.foretagskvitton.view.ArchiveAdapter;
import corp.skaj.foretagskvitton.view.SupplierAdapter;

public class MainController {

    private IMain mListener;

    public MainController(IMain listener) {
        mListener = listener;
    }

    public void setArchiveAdapterListener(final ArchiveAdapter sAdapter, final Class<?> nextActivity, final String key) {
        sAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String data = sAdapter.getData().get(position).getId();
                mListener.goToActivity(nextActivity, key, data);
            }
        });
    }

    public void setCompanyAdapterListener(final CompanyAdapter sAdapter, final Class<?> nextActivity, final String key) {
        sAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String data = sAdapter.getData().get(position).getName();
                mListener.goToActivity(nextActivity, key, data);
            }
        });
    }

    public void setCompanyAdapterListener(final SupplierAdapter sAdapter, final Class<?> nextActivity, final String key) {
        sAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String data = sAdapter.getData().get(position).getName();
                mListener.goToActivity(nextActivity, key, data);
            }
        });
    }
}
