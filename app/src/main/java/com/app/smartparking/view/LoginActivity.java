package com.app.smartparking.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.smartparking.R;
import com.app.smartparking.base.BaseActivity;
import com.app.smartparking.base.Constant;
import com.app.smartparking.model.Auth;
import com.app.smartparking.view.admin.DashboardActivity;
import com.app.smartparking.view.client.MainActivity;
import com.app.smartparking.view.client.RegisterActivity;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvRegister;

    private static final String TAG = "LoginActivityTag";

    @Override
    protected void onStart() {
        super.onStart();

        //kalau auth kosong, langsung direct ke halaman login
        if (mAuth.getCurrentUser() != null) {
            Auth auth = mSession.getAuth();

            if (auth.getRole() == Constant.ROLE_CLIENT) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            } else {
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //hentikan program kalai user tidak null
        if (mAuth.getCurrentUser() != null) return;

        init();
    }

    private void init() {
        etEmail = findViewById(R.id.editText_login_email);
        etPassword = findViewById(R.id.editText_login_password);
        btnLogin = findViewById(R.id.button_login);
        tvRegister = findViewById(R.id.textView_login_register);

        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                if (isEdittextEmpty(etEmail) || isEdittextEmpty(etPassword)) {
                    //validasi edittext
                    toast("Email atau password kosong!");
                    return;
                } else {
                    login();
                }
                break;
            case R.id.textView_login_register:
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                break;
        }
    }

    private void login() {
        //fuungsi untuk login

        //show loading
        showLoading(true);

        //get data from edittext
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        //execute fungsi nya
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        //login berhasil
                        showLoading(false);

                        //fungsi untuk validasi role user
                        checkLoginApp(task.getResult().getUser().getUid());
                    } else {
                        //login error
                        Log.d(TAG, "login: " + task.getException());

                        showLoading(false);
                        toast("Login gagal");
                    }
                });
    }

    private void checkLoginApp(String uid) {
        //role validate
        DatabaseReference reference = mDatabase.getReference(Constant.FIREBASE_TABLE_USER).child(uid);
        reference.get().addOnCompleteListener(task -> {
            //remove loading
            showLoading(false);

            //get auth data
            Auth auth = task.getResult().getValue(Auth.class);

            mSession.setAuth(auth);

            if (auth.getRole() == Constant.ROLE_CLIENT) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}