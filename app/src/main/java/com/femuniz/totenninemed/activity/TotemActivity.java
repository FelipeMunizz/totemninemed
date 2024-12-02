package com.femuniz.totenninemed.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;

import com.femuniz.totenninemed.R;
import com.femuniz.totenninemed.core.interfaces.ApiCallback;
import com.femuniz.totenninemed.core.model.SenhaToten;
import com.femuniz.totenninemed.core.model.TipoAtendimento;
import com.femuniz.totenninemed.core.model.response.RetornoGenerico;
import com.femuniz.totenninemed.core.service.TotemService;

public class TotemActivity extends BaseActivity{
    private final int IdToten = 1;
    private TotemService _service;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initializeUI();
    }

    protected void initializeUI(){
        EdgeToEdge.enable(this);
        setContentView(R.layout.layout_totem_activity);

        _service = new TotemService(this);

        Button btnPrioritario = findViewById(R.id.btn_prioritario);
        Button btnPrioritarioAgendado = findViewById(R.id.btn_prioritario_agendado);
        Button btnComum = findViewById(R.id.btn_comum);
        Button btnComumAgendado = findViewById(R.id.btn_comum_agendado);

        btnPrioritario.setOnClickListener(v -> {
            AddSenha(TipoAtendimento.Prioritario);
        });
        btnPrioritarioAgendado.setOnClickListener(v -> {
            AddSenha(TipoAtendimento.PrioritarioAgendado);
        });
        btnComum.setOnClickListener(v -> {
            AddSenha(TipoAtendimento.Comum);
        });
        btnComumAgendado.setOnClickListener(v -> {
            AddSenha(TipoAtendimento.ComumAgendado);
        });
    }

    private void AddSenha(TipoAtendimento tipoAtendimento){
        SenhaToten senhaToten = new SenhaToten();
        senhaToten.idToten = IdToten;
        senhaToten.tipoAtendimento = tipoAtendimento;
        _service.AdicionaSenhaToten(senhaToten, new ApiCallback<>() {
            @Override
            public void onSuccess(RetornoGenerico<SenhaToten> result) {

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }
}
