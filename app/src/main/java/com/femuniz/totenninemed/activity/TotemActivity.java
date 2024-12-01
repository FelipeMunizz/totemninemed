package com.femuniz.totenninemed.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;

import com.femuniz.totenninemed.R;

public class TotemActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initializeUI();
    }

    protected void initializeUI(){
        EdgeToEdge.enable(this);
        setContentView(R.layout.layout_totem_activity);
    }
}
