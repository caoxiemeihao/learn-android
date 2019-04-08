package com.atom.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        rememberPass = findViewById(R.id.remember_pass);
        login = findViewById(R.id.login);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            // 将账号和密码设置到文本框中
            accountEdit.setText(pref.getString("account", ""));
            passwordEdit.setText(pref.getString("password", ""));
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                // 假定账号是 admin 密码是 123456 表示登录成功
                if ("admin".equals(account) && "123456".equals(password)) {
                    editor = pref.edit();
                    if (rememberPass.isChecked()) {
                        editor.putString("account", account);
                        editor.putString("password", password);
                        editor.putBoolean("remember_password", true);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);;
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account or password invalid",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
