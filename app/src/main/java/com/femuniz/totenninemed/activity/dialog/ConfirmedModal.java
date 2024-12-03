package com.femuniz.totenninemed.activity.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.femuniz.totenninemed.R;
import com.google.android.material.button.MaterialButton;

import java.util.function.Consumer;

public class ConfirmedModal {
    private Activity _activity;
    private AlertDialog _alertDialog = null;

    public ConfirmedModal(Activity activity){
        this._activity = activity;
    }

    public void showConfirmedModal(String title, String message, Bitmap qrcode, Consumer<Boolean> callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(_activity);
        LayoutInflater inflater = _activity.getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.confirmed_modal, null);
        builder.setView(dialogView);
        builder.setCancelable(false);

        TextView txtTitle = dialogView.findViewById(R.id.edTitleModal);
        TextView txtMessage = dialogView.findViewById(R.id.edMessageModal);
        txtTitle.setText(title);
        txtMessage.setText(message);

        ImageView ivQrCode = dialogView.findViewById(R.id.qrCodeImageView);
        ivQrCode.setImageBitmap(qrcode);

        MaterialButton btnCancel = dialogView.findViewById(R.id.btn_modal_cancel);
        MaterialButton btnConfirm = dialogView.findViewById(R.id.btn_modal_confirmed);

        btnCancel.setOnClickListener(view -> {
            if (callback != null) callback.accept(false);
            _alertDialog.dismiss();
        });

        btnConfirm.setOnClickListener(view -> {
            if (callback != null) callback.accept(true);
            _alertDialog.dismiss();
        });

        _alertDialog = builder.create();
        _alertDialog.show();
    }
}
