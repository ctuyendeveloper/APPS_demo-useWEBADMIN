package com.example.php_asm1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.php_asm1.R;
import com.example.php_asm1.helpers.IRetrofitRouter;
import com.example.php_asm1.helpers.RetrofitHelper;
import com.example.php_asm1.models.News.NewsDetailModelResponse;
import com.example.php_asm1.models.News.NewsModelResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailNews extends AppCompatActivity {
    private final String TAG = "test";
    TextView textView1, textView2, textView3, textTopicdetail, textPosterDetail, textCreatedAtDetail;
    ImageView imageView, avatar, imagetopic;
    ScrollView scrollView;
    Integer newsId;
    IRetrofitRouter iRetrofitRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        textView1 = findViewById(R.id.textDetail1);
        textView2 = findViewById(R.id.textDetail2);
        textView3 = findViewById(R.id.labelProfile);
        textTopicdetail = findViewById(R.id.textTopicDetail);
        textPosterDetail = findViewById(R.id.textPosterDetail);
        textCreatedAtDetail = findViewById(R.id.textCreatedAtDetail);
        avatar = findViewById(R.id.avatarDetail);
        imagetopic = findViewById(R.id.postImage);
        imageView = findViewById(R.id.leftIconProfile);
        scrollView = findViewById(R.id.scrollDetail);
        scrollView.setVerticalScrollBarEnabled(false);
//        textView1.setText("Ukraine's President Zelensky to BBC: Blood money being paid for Russian oil");
//        textView2.setText("Ukrainian President Volodymyr Zelensky has accused European countries that continue to buy Russian oil of \"earning their money in other people's blood\".\n" +
//                "\n" +
//                "In an interview with the BBC, President Zelensky singled out Germany and Hungary, accusing them of blocking efforts to embargo energy sales, from which Russia stands to make up to £250bn ($326bn) this year.\n" +
//                "\n" +
//                "There has been a growing frustration among Ukraine's leadership with Berlin, which has backed some sanctions against Russia but so far resisted calls to back tougher action on oil sales.");
        textView3.setText("");
        Intent intent = getIntent();
        newsId = intent.getIntExtra("newsId", 0);
        Toast.makeText(DetailNews.this, "Số item" + newsId, Toast.LENGTH_SHORT).show();
        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailNews.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        iRetrofitRouter.getNewDetails(newsId).enqueue(getNewsDetailCallback);
        iRetrofitRouter.updateSeenValue(newsId).enqueue(updateSeenCallback);
    }

    Callback<List<NewsDetailModelResponse>> getNewsDetailCallback = new Callback<List<NewsDetailModelResponse>>() {
        @Override
        public void onResponse(Call<List<NewsDetailModelResponse>> call, Response<List<NewsDetailModelResponse>> response) {
//            Log.d(TAG, "onResponse: " + response.body());
//            Log.i(TAG, "onResponse: " + response.body());
//            Log.d(TAG, "onResponse: ");
            if (response.isSuccessful()) {
//                List<NewsDetailModelResponse> loginResponseDTO = response.body();
                NewsDetailModelResponse newsDetailModelResponse = response.body().get(0);
                textView1.setText(newsDetailModelResponse.getContent());
                textView2.setText(newsDetailModelResponse.getTitle());
                textTopicdetail.setText(newsDetailModelResponse.getName());
                textPosterDetail.setText(newsDetailModelResponse.getNAME());
                textCreatedAtDetail.setText(newsDetailModelResponse.getCreated_at());
                String imageUrl = newsDetailModelResponse.getImage();
                RequestOptions requestOptions = new RequestOptions().centerCrop();
                Glide.with(DetailNews.this)
                        .load(imageUrl)
                        .apply(requestOptions)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imagetopic);
                String imageUrl2 = newsDetailModelResponse.getAvatar();
                Glide.with(DetailNews.this)
                        .load(imageUrl2)
                        .apply(requestOptions)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(avatar);
//                loginResponseDTO.getClass();
//                            Log.i(TAG, "onResponse: " +    loginResponseDTO.getClass());
//                textView1.setText(loginResponseDTO.get());
//                textView2.setText(loginResponseDTO.getTitle());
//                Log.d(TAG, "onResponse: ????????????????" + loginResponseDTO.getNAME());
//                if (!newsDetails.isEmpty()) {
//                    // nếu thành công chuyển sang màn hình danh sách
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class ));
//                    Toast.makeText(LoginActivity.this,
//                                    "Successfully!!!", Toast.LENGTH_LONG)
//                            .show();
//                    // lưu trạng thái login/user vào shared preferences
//                    list.clear();
//                    list.addAll(loginResponseDTO);
//                    adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(DetailNews.this,
                                "Failed!!!", Toast.LENGTH_LONG)
                        .show();
            }
        }

        @Override
        public void onFailure(Call<List<NewsDetailModelResponse>> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };
    Callback<Void> updateSeenCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if (response.isSuccessful()) {
                Log.d(TAG, "updateSeenValue success");
            } else {
                Log.d(TAG, "updateSeenValue failed");
            }
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.d(TAG, "updateSeenValue onFailure: " + t.getMessage());
        }
    };
}