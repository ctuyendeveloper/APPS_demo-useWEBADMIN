package com.example.php_asm1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.php_asm1.R;
import com.example.php_asm1.helpers.RetrofitHelper;
import com.example.php_asm1.helpers.IRetrofitRouter;
import com.example.php_asm1.models.User.UserLoginRequest;
import com.example.php_asm1.models.User.UserLoginResponse;
import com.example.php_asm1.models.User.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = "login";
    Button button;
    EditText editUser, editPass;
    ImageView imageView;
    IRetrofitRouter iRetrofitRouter;

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
        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editUser.getText().toString().trim();
                String pass = editPass.getText().toString().trim();
//
//                if (user.equals("abc") && pass.equals("123"))
//                {
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class ));
//                }
//                else
//                {
//                    Toast.makeText(LoginActivity.this, "Sai pass", Toast.LENGTH_SHORT).show();
//                }
                UserLoginRequest request = new UserLoginRequest();
                request.setEmail(user);
                request.setPassword(pass);
                Log.i(TAG, "user: " + user + "pw: " + pass);

                iRetrofitRouter.login(request).enqueue(loginCallback);

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
    Callback<UserLoginResponse> loginCallback = new Callback<UserLoginResponse>() {
        @Override
        public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
            Log.d(TAG, "onResponse: " + response.message());
            if (response.isSuccessful()){
                UserLoginResponse loginResponseDTO = response.body();
//                Log.d(TAG, "onResponse: ");
                if (loginResponseDTO.getStatus()) {
                    // nếu thành công chuyển sang màn hình danh sách
                    startActivity(new Intent(LoginActivity.this, MainActivity.class ));
                    Toast.makeText(LoginActivity.this,
                                    "Successfully!!!", Toast.LENGTH_LONG)
                            .show();

                    UserModel user = loginResponseDTO.getUser();
                    // lưu trạng thái login/user vào shared preferences
                    saveUserInfo(user);
                }
                else {
                    Toast.makeText(LoginActivity.this,
                                    "Failed!!!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        }
        private void saveUserInfo(UserModel user) {
            SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // Lưu thông tin user vào SharedPreferences
            editor.putString("email", user.getEMAIL());
            editor.putString("name", user.getNAME());
            editor.putString("avatar", user.getAVATAR());
            // Các thông tin khác nếu có

            editor.apply();
        }

        @Override
        public void onFailure(Call<UserLoginResponse> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };
}