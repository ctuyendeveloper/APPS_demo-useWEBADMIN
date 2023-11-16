package com.example.php_asm1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Button button;
    EditText editUser, editPass, editPassReg;
    ImageView imageView;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editPass = findViewById(R.id.edtPasswordReg);
        editUser = findViewById(R.id.edtUsernameReg);
        editPassReg = findViewById(R.id.edtPasswordAgainReg);
        textView = findViewById(R.id.labelProfile);
        imageView = findViewById(R.id.leftIconProfile);
        textView.setText("Register");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class ));
            }
        });
        button = findViewById(R.id.buttonReg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editUser.getText().toString().trim();
                String pass1 = editPass.getText().toString().trim();
                String passAgain = editPassReg.getText().toString().trim();
                if (pass1.equals(passAgain) && !passAgain.isEmpty( ))
                {
                    // trùng lặp
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class ));
                }
                else if(user.isEmpty() && pass1.isEmpty() && passAgain.isEmpty())
                {
                    // khoảng trắng
                    Toast.makeText(RegisterActivity.this, "Khoảng trắng?", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // sai 2 pass
                    Toast.makeText(RegisterActivity.this, "Nhập đúng 2 pass không vậy?", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}