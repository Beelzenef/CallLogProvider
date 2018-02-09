package com.example.calllogsprovider;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements CallLogContract.View {

    private CallLogContract.Presenter presenter;
    private CallLogAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new CallLogPresenter(this);

        listView = (ListView) findViewById(android.R.id.list);

        adapter = new CallLogAdapter(this);
        listView.setAdapter(adapter);

        presenter.getCallLogs();
    }

    @Override
    public void setCursor(Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public Context getContext() {
        return MainActivity.this;
    }
}
