package com.example.php_asm1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class changePasswordActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        imageView = findViewById(R.id.leftIconProfile);
        textView = findViewById(R.id.labelProfile);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(changePasswordActivity.this, MainActivity.class));
            }
        });
        textView.setText("Change Password");
    }
}