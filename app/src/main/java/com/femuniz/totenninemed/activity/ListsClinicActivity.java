package com.femuniz.totenninemed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.femuniz.totenninemed.R;
import com.femuniz.totenninemed.adapter.GenericRecyclerViewAdapter;
import com.femuniz.totenninemed.core.interfaces.IApiCallback;
import com.femuniz.totenninemed.core.model.Clinica;
import com.femuniz.totenninemed.core.service.ClinicService;
import com.femuniz.totenninemed.fragment.CustomToast;

import java.util.List;

public class ListsClinicActivity extends  BaseActivity{
    private String emailUser;
    private ClinicService _clinicService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_clinic_layout);

        Intent intent = getIntent();
        emailUser = intent.getStringExtra("email");
        _clinicService = new ClinicService(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(!(emailUser== null ||emailUser.isEmpty())){
            _clinicService.ListClinicUser(emailUser, new IApiCallback<>() {
                @Override
                public void onSuccess(List<Clinica> result) {
                    GenericRecyclerViewAdapter<Clinica> adapter = new GenericRecyclerViewAdapter<>(
                            result,
                            R.layout.item_layout,
                            ((holder, item) -> {
                                if(item.id > 0){
                                    TextView txtView = holder.findViewById(R.id.tvItem);
                                    txtView.setText(item.nome);

                                    txtView.setOnClickListener(v -> {
                                        Intent intent = new Intent(ListsClinicActivity.this, ListTotemActivity.class);
                                        intent.putExtra("idClinica", item.id);
                                        startActivity(intent);

                                        finish();
                                    });
                                }
                            })
                    );
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    CustomToast.showToast(getApplicationContext(), throwable.getMessage(), R.color.error_color, R.drawable.ic_error_24dp);
                }
            });
        }
    }

}
