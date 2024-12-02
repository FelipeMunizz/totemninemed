package com.femuniz.totenninemed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat.Type;
import androidx.core.graphics.Insets;

import com.femuniz.totenninemed.R;
import com.femuniz.totenninemed.activity.dialog.ConfirmedModal;
import com.femuniz.totenninemed.activity.loader.CustomLoader;
import com.femuniz.totenninemed.core.dto.LoginUserDTO;
import com.femuniz.totenninemed.core.interfaces.ApiCallback;
import com.femuniz.totenninemed.core.model.response.RetornoToken;
import com.femuniz.totenninemed.core.service.TokenService;
import com.femuniz.totenninemed.core.service.UserService;
import com.femuniz.totenninemed.fragment.CustomToast;

public class LoginActivity extends AppCompatActivity {
    private UserService _userService;
    private CustomLoader _customLoader;
    private TokenService _tokenService;
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

        _userService = new UserService(this);
        _customLoader = new CustomLoader(this);
        _tokenService = new TokenService(this);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            _customLoader.showLoader(false);

            EditText etEmail = findViewById(R.id.etLogin);
            EditText etPassword = findViewById(R.id.etSenha);

            LoginUserDTO model = new LoginUserDTO();
            model.Email = etEmail.getText().toString();
            model.Password = etPassword.getText().toString();

            loginRequest(model);
        });
    }

    private void loginRequest(LoginUserDTO model){
        _userService.Login(model, new ApiCallback<>() {
            @Override
            public void onSuccess(RetornoToken result) {
                if (result == null || result.getToken() == null || result.getToken().isEmpty()) {
                    CustomToast.showToast(
                            getApplicationContext(),
                            "Usuário não autenticado",
                            R.color.error_color,
                            R.drawable.ic_error_24dp
                    );
                    _customLoader.hideLoader();
                } else {
                    _tokenService.saveToken(result.getToken());
                    _customLoader.hideLoader();

                    startActivity(new Intent(LoginActivity.this, TotemActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                    finish();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                CustomToast.showToast(
                        getApplicationContext(),
                        throwable.getMessage(),
                        R.color.error_color,
                        R.drawable.ic_error_24dp
                );
                _customLoader.hideLoader();
            }
        });
    }

    protected  void showLoader(boolean cancelable){
        _customLoader.showLoader(cancelable);
    }

    protected  void hidLoader(){
        _customLoader.hideLoader();
    }
}
