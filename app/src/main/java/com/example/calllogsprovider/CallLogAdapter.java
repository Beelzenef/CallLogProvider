package com.example.calllogsprovider;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by usuario on 9/02/18.
 */

class CallLogAdapter extends CursorAdapter {

    public CallLogAdapter(Context context) {
        super(context, null, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_call, viewGroup, false);

        CallLogHolder callLogHolder = new CallLogHolder();

        callLogHolder.txtV_Duracion = view.findViewById(R.id.txtV_Duracion);
        callLogHolder.txtV_Numero = view.findViewById(R.id.txtV_Numero);
        callLogHolder.txtV_Fecha = view.findViewById(R.id.txtV_Fecha);
        callLogHolder.txtV_Tipo = view.findViewById(R.id.txtV_Tipo);

        view.setTag(callLogHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        CallLogHolder callLogHolder = (CallLogHolder) view.getTag();
        callLogHolder.txtV_Numero.setText("Número: " + cursor.getString(1));
        callLogHolder.txtV_Fecha.setText("Fecha: " + dateFormater(Long.parseLong(cursor.getString(2))));
        callLogHolder.txtV_Duracion.setText("Duracion: " + cursor.getString(3));
        callLogHolder.txtV_Tipo.setText("Tipo: " + tipoLlamada(Integer.parseInt(cursor.getString(4))));
    }

    private String tipoLlamada(int valorRecibido) {
        String tipoLlamada = "";

        switch (valorRecibido) {
            case CallLog.Calls.INCOMING_TYPE:
                tipoLlamada = "Entrante";
                break;
            case CallLog.Calls.MISSED_TYPE:
                tipoLlamada = "Perdida";
                break;
            case CallLog.Calls.OUTGOING_TYPE:
                tipoLlamada = "Saliente";
                break;
        }

        return tipoLlamada;
    }

    private String dateFormater(Long seconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMMM, yyyy");
        return formatter.format(new Date(seconds));
    }

    private class CallLogHolder {
        private TextView txtV_Numero;
        private TextView txtV_Fecha;
        private TextView txtV_Duracion;
        private TextView txtV_Tipo;
    }
}
