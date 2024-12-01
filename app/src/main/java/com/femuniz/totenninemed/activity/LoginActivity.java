package com.femuniz.totenninemed.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat.Type;
import androidx.core.graphics.Insets;

import com.femuniz.totenninemed.R;
import com.femuniz.totenninemed.activity.loader.CustomLoader;

public class LoginActivity extends AppCompatActivity {
    private CustomLoader _customLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_activity);
        View rootView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets systemBars = insets.getInsets(Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    protected  void showLoader(boolean cancelable){
        _customLoader.showLoader(cancelable);
    }

    protected  void hidLoader(){
        _customLoader.hideLoader();
    }
}
