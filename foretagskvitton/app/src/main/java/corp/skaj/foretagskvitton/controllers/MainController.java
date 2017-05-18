package corp.skaj.foretagskvitton.controllers;

import android.view.View;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

import corp.skaj.foretagskvitton.view.ArchiveAdapter;
import corp.skaj.foretagskvitton.view.CompanyAdapter;
import corp.skaj.foretagskvitton.view.IController;

public class MainController<T> implements IController<T> {

    private IMain mListener;

    public MainController(IMain listener) {
        mListener = listener;
    }

    @Override
    public void setListener(UltimateRecyclerviewViewHolder<T> view, final String data, final String key) {


    }

    /*
    new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (key) {
                    case CompanyAdapter.COMPANY_KEY:
                        mListener.goToCompany(data);
                        return;
                    case ArchiveAdapter.ARCHIVE_KEY:
                        mListener.goToPurchase(data);
                        return;
                    case "Supplier":
                        // TODO FIX KEY ETC
                    default:
                        return;
                }
            }
        }
     */



}
