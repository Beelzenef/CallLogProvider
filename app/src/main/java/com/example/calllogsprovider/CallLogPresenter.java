package com.example.calllogsprovider;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;

/**
 * Created by usuario on 9/02/18.
 */

public class CallLogPresenter implements LoaderManager.LoaderCallbacks<Cursor>, CallLogContract.Presenter {

    private CallLogContract.View view;
    private static final int CALLLOG = 0;

    public CallLogPresenter(CallLogContract.View view) {
        this.view = view;
    }

    public void getCallLogs() {
        ((Activity) view.getContext()).getLoaderManager().initLoader(CALLLOG, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        CursorLoader cursorLoader = null;

        switch (i) {
            case CALLLOG:
                String stringOrder = CallLog.Calls.DATE + " DESC";
                String[] projection = {CallLog.Calls._ID, CallLog.Calls.NUMBER, CallLog.Calls.DATE, CallLog.Calls.DURATION, CallLog.Calls.TYPE};
                cursorLoader = new CursorLoader(view.getContext(), Uri.parse("content://call_log/calls"), projection, null, null, stringOrder);
                break;
        }

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        view.setCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setCursor(null);
    }
}
