package com.example.php_asm1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.php_asm1.R;
import com.example.php_asm1.helpers.IRetrofitRouter;
import com.example.php_asm1.helpers.RetrofitHelper;
import com.example.php_asm1.models.ForgotPassword.ForgotPasswordRequest;
import com.example.php_asm1.models.ForgotPassword.ForgotPasswordResponse;
import com.example.php_asm1.models.User.UserLoginRequest;
import com.example.php_asm1.models.User.UserLoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassActivity extends AppCompatActivity {
    IRetrofitRouter iRetrofitRouter;
    private final String TAG = "login";
    Button button;
    EditText editUserForget;
    ProgressBar progressBar;
    ImageView imageView;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        editUserForget = findViewById(R.id.edtUsernameForget);
        textView = findViewById(R.id.labelProfile);
        imageView = findViewById(R.id.leftIconProfile);
        button = findViewById(R.id.buttonForgotPass);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        textView.setText("Forget Password");
        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPassActivity.this, LoginActivity.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtForget = editUserForget.getText().toString().trim();
                ForgotPasswordRequest request = new ForgotPasswordRequest();
                request.setEmail(edtForget);
                progressBar.setVisibility(View.VISIBLE);
                iRetrofitRouter.forgot(request).enqueue(loginCallback);
            }
        });
    }

    Callback<ForgotPasswordResponse> loginCallback = new Callback<ForgotPasswordResponse>() {
        @Override
        public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
//            Log.d(TAG, "onResponse: " + response.message());

            if (response.isSuccessful()) {
                ForgotPasswordResponse loginResponseDTO = response.body();
                Log.d(TAG, "onResponse:123123 " + loginResponseDTO.getStatus());
                if (loginResponseDTO.getStatus()) {
//                    // nếu thành công chuyển sang màn hình danh sách
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class ));
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ForgetPassActivity.this, loginResponseDTO.getMessage(), Toast.LENGTH_LONG)
                            .show();
//                    // lưu trạng thái login/user vào shared preferences
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ForgetPassActivity.this,
                                    loginResponseDTO.getMessage(), Toast.LENGTH_LONG)
                            .show();
                }
            }
        }

        @Override
        public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };
}