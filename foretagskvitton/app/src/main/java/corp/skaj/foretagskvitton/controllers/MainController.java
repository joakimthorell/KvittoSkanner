package corp.skaj.foretagskvitton.controllers;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

import corp.skaj.foretagskvitton.view.IAdapterController;

public class MainController<T> implements IAdapterController<T> {

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
