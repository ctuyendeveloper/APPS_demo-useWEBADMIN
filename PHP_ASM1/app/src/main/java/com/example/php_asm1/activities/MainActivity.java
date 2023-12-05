package com.example.php_asm1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.php_asm1.R;
import com.example.php_asm1.adapters.itemAdapter;
import com.example.php_asm1.helpers.IRetrofitRouter;
import com.example.php_asm1.helpers.RetrofitHelper;
import com.example.php_asm1.models.News.NewsModelResponse;
import com.example.php_asm1.models.User.UserLoginResponse;
import com.example.php_asm1.models.itemtintuc;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "main";
    Integer a;
//    Context context;
    ListView ls;
    itemAdapter adapter;
    List<NewsModelResponse> list;
    LinearLayout test;
    TextView textView, imgTopic, imgTopicTitle, tvPosterTreding, tvTimer, tvTrendingSeen;
    ImageView backr2, imgTrend;

    IRetrofitRouter iRetrofitRouter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ls = findViewById(R.id.lv);
        imgTrend = findViewById(R.id.imgTrending);
        imgTopic = findViewById(R.id.TvTopicId);
        imgTopicTitle = findViewById(R.id.TvTopicTitle);
        tvPosterTreding = findViewById(R.id.tvPosterTreding);
        tvTimer = findViewById(R.id.Timer);
        test = findViewById(R.id.linear);
        tvTrendingSeen = findViewById(R.id.TrendingSeen);

//        List<itemtintuc> list = new ArrayList<>();
//        list.add(new itemtintuc(R.drawable.image2, "Travel",
//                "Ukraine's President Zelensky to BBC: Blood money being paid...", "Huy Hoàng", "3h ago"));
//        list.add(new itemtintuc(R.drawable.image2, "Travel",
//                "Ukraine's President Zelensky to BBC: Blood money being paid...", "Huy Hoàng", "3h ago"));
//        list.add(new itemtintuc(R.drawable.image2, "Travel",
//                "Ukraine's President Zelensky to BBC: Blood money being paid...", "Huy Hoàng", "3h ago"));
//        adapter = new itemAdapter(list, this);
        list = new ArrayList<>();
        adapter = new itemAdapter(list, this);

        ls.setAdapter(adapter);
        ls.setVerticalScrollBarEnabled(false);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = (String) parent.getItemAtPosition(position);
                NewsModelResponse selectedNews = list.get(position);

                // Tạo Intent để chuyển sang màn hình mới
                Intent intent = new Intent(MainActivity.this, DetailNews.class);
                intent.putExtra("newsId", selectedNews.getId());
                // Bắt đầu Activity mới
                startActivity(intent);
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailNews.class);
                intent.putExtra("newsId", a);
                startActivity(intent);
            }
        });
        textView = findViewById(R.id.tvToolbarMain);
        backr2 = findViewById(R.id.righticon);
        backr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });
        textView.setText("News");
        iRetrofitRouter = RetrofitHelper.createService(IRetrofitRouter.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        iRetrofitRouter.getNews().enqueue(getNewsCallback);
        iRetrofitRouter.getNewsTrengding().enqueue(getNewsTrendingCallback);
    }

    Callback<List<NewsModelResponse>> getNewsCallback = new Callback<List<NewsModelResponse>>() {
        @Override
        public void onResponse(Call<List<NewsModelResponse>> call, Response<List<NewsModelResponse>> response) {
            Log.d(TAG, "onResponse: " + response.message());
            if (response.isSuccessful()){
                List<NewsModelResponse> loginResponseDTO = response.body();
                Log.d(TAG, "onResponse: " + loginResponseDTO);
                if (loginResponseDTO != null && loginResponseDTO.size() > 0) {
//                    // nếu thành công chuyển sang màn hình danh sách
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class ));
//                    Toast.makeText(LoginActivity.this,
//                                    "Successfully!!!", Toast.LENGTH_LONG)
//                            .show();
//                    // lưu trạng thái login/user vào shared preferences
                    list.clear();
                    list.addAll(loginResponseDTO);
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this,
                                    "Failed!!!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        }

        @Override
        public void onFailure(Call<List<NewsModelResponse>> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };
    Callback<NewsModelResponse> getNewsTrendingCallback = new Callback<NewsModelResponse>() {
        @Override
        public void onResponse(Call<NewsModelResponse> call, Response<NewsModelResponse> response) {
            Log.d(TAG, "onResponse: " + response.message());
            if (response.isSuccessful()){
                NewsModelResponse loginResponseDTO = response.body();
                Log.d(TAG, "onResponse123123123: " + loginResponseDTO.getCreated_at());
                if (loginResponseDTO != null) {
                    a = loginResponseDTO.getId();
                    imgTopic.setText(loginResponseDTO.getName());
                    imgTopicTitle.setText(loginResponseDTO.getTitle());
                    tvPosterTreding.setText(loginResponseDTO.getNAME());
                    tvTimer.setText(loginResponseDTO.getCreated_at());
                    tvTrendingSeen.setText("Đã xem :" + loginResponseDTO.getSeen());
                    String imageUrl = loginResponseDTO.getImage();
                    RequestOptions requestOptions = new RequestOptions().centerCrop();
                    Glide.with(MainActivity.this)
                            .load(imageUrl)
                            .apply(requestOptions)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(imgTrend);
//                    // nếu thành công chuyển sang màn hình danh sách
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class ));
//                    Toast.makeText(LoginActivity.this,
//                                    "Successfully!!!", Toast.LENGTH_LONG)
//                            .show();
//                    // lưu trạng thái login/user vào shared preferences
//                    list.clear();
//                    list.addAll(loginResponseDTO);
//                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this,
                                    "Failed!!!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        }

        @Override
        public void onFailure(Call<NewsModelResponse> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };
    private void showMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu, popupMenu.getMenu());

        // Đặt sự kiện cho các mục menu nếu cần
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.profile) {
                    // Xử lý khi chọn menu_item1
//                    Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    return true;
                } else if (itemId == R.id.chgPass) {
                    // Xử lý khi chọn menu_item2
//                    Toast.makeText(MainActivity.this, "Change Password", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, changePasswordActivity.class));
                    return true;
                }else if (itemId == R.id.Logout) {
                    // Xử lý khi chọn menu_item2
                    startActivity(new Intent(MainActivity.this, LoginActivity.class ));
                    return true;
                } else {
                    return false;
                }
            }
        });
        popupMenu.show();
    }
}