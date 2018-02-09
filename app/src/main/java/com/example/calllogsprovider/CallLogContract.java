package com.example.calllogsprovider;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by usuario on 9/02/18.
 */

public interface CallLogContract {

    interface View {
        void setCursor(Cursor cursor);
        Context getContext();
    }

    interface Presenter {
        void getCallLogs();
    }
}
