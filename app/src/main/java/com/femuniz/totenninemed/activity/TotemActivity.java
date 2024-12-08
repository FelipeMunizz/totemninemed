package com.femuniz.totenninemed.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;

import com.femuniz.totenninemed.R;
import com.femuniz.totenninemed.core.interfaces.IApiCallback;
import com.femuniz.totenninemed.core.model.SenhaToten;
import com.femuniz.totenninemed.core.model.response.RetornoGenerico;
import com.femuniz.totenninemed.core.service.TotemService;
import com.femuniz.totenninemed.fragment.CustomToast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Base64;

public class TotemActivity extends BaseActivity{
    private int IdToten;
    private TotemService _service;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initializeUI();

        Intent intent = getIntent();
        IdToten = intent.getIntExtra("idTotem", 0);
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
            AddSenha(0);
        });
        btnPrioritarioAgendado.setOnClickListener(v -> {
            AddSenha(1);
        });
        btnComum.setOnClickListener(v -> {
            AddSenha(2);
        });
        btnComumAgendado.setOnClickListener(v -> {
            AddSenha(3);
        });
    }

    private void AddSenha(int tipoAtendimento){
        SenhaToten senhaToten = new SenhaToten();
        senhaToten.idToten = IdToten;
        senhaToten.tipoAtendimento = tipoAtendimento;
        _service.AdicionaSenhaToten(senhaToten, new IApiCallback<>() {
            @Override
            public void onSuccess(RetornoGenerico<SenhaToten> result) {
                SenhaToten senha = result.result;
                if(result.success){
                    try {
                        String param = "senha=" + senha.senhaPainel + "&dataSenha" + senha.dataHoraCriacao;
                        String base64Param = Base64.getEncoder().encodeToString(param.getBytes());
                        String baseString = "https://brave-moss-08c07190f.4.azurestaticapps.net?" + "params=" + base64Param;
                        BarcodeEncoder encoder = new BarcodeEncoder();
                        Bitmap qrcode = encoder.encodeBitmap(baseString, BarcodeFormat.QR_CODE, 200, 200);

                        showConfirmedModal("SENHA: " + senha.senhaPainel, "Deseja imprimir a senha", qrcode, isConfirmed ->{
                            if(isConfirmed)
                                CustomToast.showToast(getApplicationContext(),"Senha Impressa", R.color.success_color, R.drawable.ic_done_24dp);
                            //Implementar impressao caso o dispositivo tenha
                        });
                    } catch (WriterException e) {
                        CustomToast.showToast(getApplicationContext(), e.getMessage(), R.color.error_color, R.drawable.ic_error_24dp);
                    }
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed(){

    }
}
