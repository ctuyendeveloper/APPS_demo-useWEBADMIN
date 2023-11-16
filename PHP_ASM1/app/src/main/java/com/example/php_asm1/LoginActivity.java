package com.example.php_asm1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button button;
    EditText editUser, editPass;
    ImageView imageView;

    TextView textView, textView2, textView3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editPass = findViewById(R.id.edtPassword);
        editUser = findViewById(R.id.edtUsername);
        button = findViewById(R.id.Buttonlg);
        textView = findViewById(R.id.textGoReg);
        textView2 = findViewById(R.id.tvToolbarMain);
        textView3 = findViewById(R.id.textGoForget);
        imageView = findViewById(R.id.righticon);
        textView2.setText("Login");
        imageView.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editUser.getText().toString().trim();
                String pass = editPass.getText().toString().trim();

                if (user.equals("abc") && pass.equals("123"))
                {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class ));
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Sai pass", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class ));
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPassActivity.class ));
            }
        });
    }
}