package com.femuniz.totenninemed.activity.loader;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.femuniz.totenninemed.R;

public class CustomLoader {
    Activity _activity;
    AlertDialog _alertDialog;

    public CustomLoader(Activity activity){
        _activity = activity;
    }

    public void showLoader(boolean cancelable){
        AlertDialog.Builder builder = new AlertDialog.Builder(_activity);

        LayoutInflater inflater = _activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_loader, null));
        builder.setCancelable(cancelable);
        _alertDialog = builder.create();

//        if(!message.isEmpty()){
//            TextView txtMessage = _alertDialog.findViewById(R.id.txtLoader);
//            txtMessage.setText(message);
//        }

        _alertDialog.show();
    }

    public void hideLoader(){
        _alertDialog.dismiss();
    }
}
