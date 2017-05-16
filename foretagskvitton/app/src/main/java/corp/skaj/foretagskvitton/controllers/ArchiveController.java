package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class ArchiveController<T> {
    public static final String ITEM_ID = "receipt_id";

    public void setItemListener(View itemView, final Class<T> nextClassToStart,final Context context) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Mabye it works", Toast.LENGTH_SHORT).show();
                Intent intent =  new Intent(context, nextClassToStart);
                intent.putExtra(ITEM_ID, "lars");
                context.startActivity(intent);
            }
        });
    }

}