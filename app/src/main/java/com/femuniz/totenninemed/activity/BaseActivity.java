package com.femuniz.totenninemed.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.femuniz.totenninemed.R;
import com.femuniz.totenninemed.activity.loader.CustomLoader;
import com.femuniz.totenninemed.fragment.CustomToast;

public class BaseActivity extends AppCompatActivity {
    private CustomLoader _customLoader;
    @Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    _customLoader = new CustomLoader(this);
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

    /**
     * Redireciona o clique no botão de voltar no Toolbar.
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Método abstrato para forçar as subclasses a implementar a inicialização da UI.
     */
    protected void initializeUI() {

    }

    public enum TypeToast{
        Success,
        Warning,
        Error
    }
}
