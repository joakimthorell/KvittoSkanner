package corp.skaj.foretagskvitton.view;

import android.widget.Button;

public interface ICompany {
    void setEditEmployeeListener (Button button);
    void setRemoveEmployeeListener (Button button);
    void setEditCardListener (Button button);
    void setRemoveCardListener (Button button);
}
