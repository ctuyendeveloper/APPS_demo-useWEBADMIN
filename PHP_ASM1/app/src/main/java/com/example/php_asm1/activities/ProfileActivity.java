package com.example.php_asm1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.php_asm1.R;
import com.example.php_asm1.helpers.IRetrofitRouter;
import com.example.php_asm1.helpers.RetrofitHelper;

import retrofit2.Call;

public class ProfileActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    ImageView imageView, imageView2, avatarImg;
    TextView textView;
    EditText fullName, emailAddress;
//    Button btnUpdate;
    IRetrofitRouter iRetrofitRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imageView = findViewById(R.id.leftIconProfile);
        imageView2 = findViewById(R.id.rightIconProfile);
        avatarImg = findViewById(R.id.avatar);
        textView = findViewById(R.id.labelProfile);
        fullName = findViewById(R.id.editFullnameProfile);
//        btnUpdate = findViewById(R.id.btnUpdateProfile);
        emailAddress = findViewById(R.id.edtEmailProfile);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
        textView.setText("Profile");
        imageView2.setVisibility(View.GONE);
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("name", null);
        String email = sharedPreferences.getString("email", null);
        String avatar = sharedPreferences.getString("avatar", null);
        fullName.setText(username);
        emailAddress.setText(email);
        fullName.setText(username);
        Glide.with(this)
                .load(avatar)
                .placeholder(R.drawable.baseline_error_24) // Drawable mặc định nếu không thể tải được hình ảnh
                .error(R.drawable.baseline_error_24) // Drawable mặc định nếu có lỗi trong quá trình tải
                .into(avatarImg);
//        avatarImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openGallery();
//            }
//        });
        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);
    }
//    private void openGallery() {
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, PICK_IMAGE_REQUEST);
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
//            Uri selectedImageUri = data.getData();
//
//            // Hiển thị hình ảnh được chọn lên ImageView sử dụng Glide hoặc thư viện khác
//            loadImage(selectedImageUri);
//
//            btnUpdate.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    updateImageUrl(selectedImageUri, );
//                }
//            });
//
//
//        }
//    }
//    private void loadImage(Uri imageUri) {
//        // Sử dụng Glide hoặc thư viện khác để hiển thị hình ảnh từ Uri lên ImageView
//        Glide.with(this)
//                .load(imageUri)
//                .placeholder(R.drawable.baseline_error_24)
//                .error(R.drawable.baseline_error_24)
//                .into(avatarImg);
//    }
//    private void updateImageUrl(String newImageUrl, String authToken) {
//        ImageUpdateRequest updateRequest = new ImageUpdateRequest(newImageUrl);
//
//        Call<Void> call = apiService.updateImageUrl("Bearer " + authToken, updateRequest);
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (response.isSuccessful()) {
//                    // Cập nhật thành công
//                    Toast.makeText(YourActivity.this, "Image URL updated successfully!", Toast.LENGTH_SHORT).show();
//                } else {
//                    // Xử lý lỗi
//                    Toast.makeText(YourActivity.this, "Failed to update image URL", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                // Xử lý lỗi kết nối
//                Toast.makeText(YourActivity.this, "Network error", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}