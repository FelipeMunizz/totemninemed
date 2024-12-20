package com.femuniz.totenninemed.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.femuniz.totenninemed.R;
import com.femuniz.totenninemed.activity.dialog.ConfirmedModal;
import com.femuniz.totenninemed.activity.loader.CustomLoader;
import com.femuniz.totenninemed.core.service.TokenService;
import com.femuniz.totenninemed.fragment.CustomToast;

import java.util.function.Consumer;

public class BaseActivity extends AppCompatActivity {
    private CustomLoader _customLoader;
    private TokenService _tokenService;
    @Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    _customLoader = new CustomLoader(this);
    _tokenService = new TokenService(this);

    validateUser();
}
    /**
     * Exibe um loader
     * @param cancelable Se o loader pode ser cancelado
     */
    protected void showLoader(boolean cancelable){
        _customLoader.showLoader(cancelable);
    }

    /**
     * Fecha o loader
     */
    protected void hideLoader(){
        _customLoader.hideLoader();
    }
    /**
     * Exibe uma mensagem Toast.
     *
     * @param message A mensagem a ser exibida.
     */
    protected void showToast(String message, TypeToast typeToast) {
        switch (typeToast){
            case Success:
                CustomToast.showToast(getApplicationContext(), message, R.color.success_color, R.drawable.ic_done_24dp);
                break;
            case Warning:
                CustomToast.showToast(getApplicationContext(), message, R.color.warning_color, R.drawable.ic_warning_24dp);
                break;
            case Error:
                CustomToast.showToast(getApplicationContext(), message, R.color.error_color, R.drawable.ic_error_24dp);
                break;
        }
    }

    /**
     * Modal para confirmação
     * @param title
     * @param message
     * @param callback
     */
    protected void showConfirmedModal(String title, String message, Bitmap qrcode, Consumer<Boolean> callback) {
        ConfirmedModal confirmedModal = new ConfirmedModal(this);
        confirmedModal.showConfirmedModal(title, message, qrcode, isConfirmed -> {

            if (callback != null) {
                callback.accept(isConfirmed);
            }
        });
    }


    /**
     * Método genérico para redirecionar para outra Activity
     * @param targetActivity
     * @param clearTask
     */
    protected void redirectTo(Class<?> targetActivity, boolean clearTask) {
        Intent intent = new Intent(this, targetActivity);

        if (clearTask) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }

        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        finish();
    }

    /**
     * Verifica se há conexão com a Internet.
     *
     * @return true se conectado, false caso contrário.
     */
    protected boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    private void validateUser(){
        if(!_tokenService.isValidToken())
            redirectToLogin();
    }

    private void redirectToLogin(){
        _tokenService.removeToken();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        finish();
    }
    /**
     * Redireciona o clique no botão de voltar no Toolbar.
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public enum TypeToast{
        Success,
        Warning,
        Error
    }
}
