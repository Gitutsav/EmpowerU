package com.example.lenovo.empoweru;

import android.app.ProgressDialog;
import android.content.Context;

public class progressAlertDialog {
    public static ProgressDialog showProgressDialog(Context context, String message){
        ProgressDialog m_Dialog = new ProgressDialog(context);
        m_Dialog.setMessage(message);
        m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        m_Dialog.setCancelable(false);
        m_Dialog.show();
        return m_Dialog;
    }

}