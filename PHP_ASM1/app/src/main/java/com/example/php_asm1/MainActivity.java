package com.example.php_asm1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView ls;
    itemAdapter adapter;
    TextView textView;
    ImageView backr2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ls = findViewById(R.id.lv);
        List<itemtintuc> list = new ArrayList<>();
        list.add(new itemtintuc(R.drawable.image2, "Travel",
                "Ukraine's President Zelensky to BBC: Blood money being paid...", "Huy Hoàng", "3h ago"));
        list.add(new itemtintuc(R.drawable.image2, "Travel",
                "Ukraine's President Zelensky to BBC: Blood money being paid...", "Huy Hoàng", "3h ago"));
        list.add(new itemtintuc(R.drawable.image2, "Travel",
                "Ukraine's President Zelensky to BBC: Blood money being paid...", "Huy Hoàng", "3h ago"));
        adapter = new itemAdapter(list, this);
        ls.setAdapter(adapter);
        textView = findViewById(R.id.tvToolbarMain);
        backr2 = findViewById(R.id.righticon);
        backr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });
        textView.setText("News");

    }
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