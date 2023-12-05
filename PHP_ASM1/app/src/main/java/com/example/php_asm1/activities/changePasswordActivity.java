package com.example.php_asm1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.php_asm1.R;

public class changePasswordActivity extends AppCompatActivity {
    ImageView imageView, imageView2;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        imageView = findViewById(R.id.leftIconProfile);
        imageView2 = findViewById(R.id.rightIconProfile);
        textView = findViewById(R.id.labelProfile);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(changePasswordActivity.this, MainActivity.class));
            }
        });
        textView.setText("Change Password");
        imageView2.setVisibility(View.GONE);
    }
}