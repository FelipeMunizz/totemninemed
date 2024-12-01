package com.femuniz.totenninemed.fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.femuniz.totenninemed.R;

public class CustomToast {
    public static void showToast(Context context, String message, int color, int icoResId){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View layout = layoutInflater.inflate(R.layout.custom_toast, null);

        TextView textView = layout.findViewById(R.id.toast_message);
        textView.setText(message);

        ImageView imageView = layout.findViewById(R.id.toast_icon);
        imageView.setImageResource(icoResId);

        layout.setBackground(new ColorDrawable(color));

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.END, 50, 100);
        toast.setView(layout);
        toast.show();
    }
}
