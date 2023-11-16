package com.example.php_asm1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ForgetPassActivity extends AppCompatActivity {
    Button button;
    EditText editUserForget;
    ImageView imageView;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        editUserForget = findViewById(R.id.edtPasswordReg);
        textView = findViewById(R.id.labelProfile);
        imageView = findViewById(R.id.leftIconProfile);
        textView.setText("Forget Password");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPassActivity.this, LoginActivity.class ));
            }
        });
    }
}