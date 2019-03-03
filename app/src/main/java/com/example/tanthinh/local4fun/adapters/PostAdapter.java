package com.example.tanthinh.local4fun.adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.tanthinh.local4fun.R;
import com.example.tanthinh.local4fun.models.Post;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private List postList; // Temporary List
    private Context context;
    private ViewPagerAdapter viewPagerAdapter;
    private CircleIndicator indicator;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ViewPager viewPager; // for slider
        public TextView textView;

        public MyViewHolder(View v) {
            super(v);
            viewPager = v.findViewById(R.id.viewPager);
            textView = v.findViewById(R.id.fakeText);
        }
    }

    public PostAdapter(Context context, List postList)
    {
        this.postList = postList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_block, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        indicator = (CircleIndicator) v.findViewById(R.id.viewPagerIndicator);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        viewPagerAdapter = new ViewPagerAdapter(context, ((Post)postList.get(position)).getPictures());
        holder.viewPager.setAdapter(viewPagerAdapter);
        indicator.setViewPager(holder.viewPager);
        viewPagerAdapter.registerDataSetObserver(indicator.getDataSetObserver());

        holder.textView.setText(((Post)postList.get(position)).getTitle() );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}