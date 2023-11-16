package com.example.php_asm1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class itemAdapter extends BaseAdapter {
    private List<itemtintuc> list;
    private Context context;

    public itemAdapter(List<itemtintuc> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public static class LogoviewHolder
    {
        public ImageView imageView;
        public TextView topicadapter;
        public TextView postadapter;
        public TextView posteradapter;
        public TextView timepostadapter;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LogoviewHolder holder = null;
        if (view == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(R.layout.itemtintuc, null);
            holder = new LogoviewHolder();
            holder.imageView = view.findViewById(R.id.itemImg);
            holder.topicadapter = view.findViewById(R.id.itemTopic);
            holder.postadapter = view.findViewById(R.id.itemPost);
            holder.posteradapter = view.findViewById(R.id.itemPoster);
            holder.timepostadapter = view.findViewById(R.id.itemTimer);
            view.setTag(holder);
        }
        else
        {
            holder = (LogoviewHolder) view.getTag();
        }
        holder.topicadapter.setText(list.get(i).getTopic());
        holder.postadapter.setText(list.get(i).getPost());
        holder.posteradapter.setText(list.get(i).getPoster());
        holder.timepostadapter.setText(list.get(i).getTimepost());
        holder.imageView.setImageResource(list.get(i).getImageId());
        return view;
    }
}
