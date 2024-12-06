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
import com.femuniz.totenninemed.core.model.Toten;
import com.femuniz.totenninemed.core.service.ClinicService;
import com.femuniz.totenninemed.core.service.TotemService;
import com.femuniz.totenninemed.fragment.CustomToast;

import java.util.List;

public class ListTotemActivity extends BaseActivity{
    private int idClinica;
    private TotemService _totemService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_clinic_layout);

        Intent intent = getIntent();
        idClinica = intent.getIntExtra("idClinica", 0);
        _totemService = new TotemService(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(idClinica > 0){
            _totemService.ListaTotensClinica(idClinica, new IApiCallback<>() {
                @Override
                public void onSuccess(List<Toten> result) {
                    GenericRecyclerViewAdapter<Toten> adapter = new GenericRecyclerViewAdapter<>(
                            result,
                            R.layout.item_layout,
                            ((holder, item) -> {
                                if(item.id > 0){
                                    TextView txtView = holder.findViewById(R.id.tvItem);
                                    txtView.setText(item.nome);

                                    txtView.setOnClickListener(v -> {
                                        Intent intent = new Intent(ListTotemActivity.this, TotemActivity.class);
                                        intent.putExtra("idTotem", item.id);
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
