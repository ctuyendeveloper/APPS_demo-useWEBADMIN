package com.example.php_asm1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.php_asm1.R;
import com.example.php_asm1.helpers.IRetrofitRouter;
import com.example.php_asm1.helpers.RetrofitHelper;
import com.example.php_asm1.models.ForgotPassword.ForgotPasswordRequest;
import com.example.php_asm1.models.ForgotPassword.ForgotPasswordResponse;
import com.example.php_asm1.models.Register.RegisterRequest;
import com.example.php_asm1.models.Register.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    IRetrofitRouter iRetrofitRouter;
    private final String TAG = "login";
    Button button;
    EditText editUser, editPass, editPassReg, editNameReg;
    ImageView imageView;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editPass = findViewById(R.id.edtPasswordReg);
        editUser = findViewById(R.id.edtUsernameReg);
        editPassReg = findViewById(R.id.edtPasswordAgainReg);
        editNameReg = findViewById(R.id.edtNameReg);
        textView = findViewById(R.id.labelProfile);
        imageView = findViewById(R.id.leftIconProfile);
        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);
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
                String name = editNameReg.getText().toString().trim();
//                if (pass1.equals(passAgain) && !passAgain.isEmpty( ))
//                {
//                    // trùng lặp
//                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class ));
//                }
//                else if(user.isEmpty() && pass1.isEmpty() && passAgain.isEmpty())
//                {
//                    // khoảng trắng
//                    Toast.makeText(RegisterActivity.this, "Khoảng trắng?", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    // sai 2 pass
//                    Toast.makeText(RegisterActivity.this, "Nhập đúng 2 pass không vậy?", Toast.LENGTH_SHORT).show();
//                }
                RegisterRequest request = new RegisterRequest();
                request.setEmail(user);
                request.setPassword(pass1);
                request.setPassword_confirm(passAgain);
                request.setName(name);
//                progressBar.setVisibility(View.VISIBLE);
                iRetrofitRouter.register(request).enqueue(loginCallback);
            }
        });
    }
    Callback<RegisterResponse> loginCallback = new Callback<RegisterResponse>() {
        @Override
        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
//            Log.d(TAG, "onResponse: " + response.message());

            if (response.isSuccessful()) {
                RegisterResponse loginResponseDTO = response.body();
//                Log.d(TAG, "onResponse:123123 " + loginResponseDTO.getStatus());
                if (loginResponseDTO.getStatus()) {
//                    // nếu thành công chuyển sang màn hình danh sách
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class ));
//                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this, loginResponseDTO.getMessage(), Toast.LENGTH_LONG)
                            .show();
//                    // lưu trạng thái login/user vào shared preferences
                } else {
//                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this,
                                    loginResponseDTO.getMessage(), Toast.LENGTH_LONG)
                            .show();
                }
            }
        }

        @Override
        public void onFailure(Call<RegisterResponse> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };
}