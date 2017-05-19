package corp.skaj.foretagskvitton.view;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

public interface IAdapterController<T> {

    void setListener(UltimateRecyclerviewViewHolder<T> view, final String data, final String key);
}
