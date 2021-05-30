package com.app.smartparking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.smartparking.R;
import com.app.smartparking.model.Setting;
import com.bumptech.glide.Glide;

import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingHolder> {

    private final Context mContext;
    private final List<Setting> mData;
    private final ClickHandler mHandler;

    public SettingAdapter(Context mContext, List<Setting> mData, ClickHandler mHandler) {
        this.mContext = mContext;
        this.mData = mData;
        this.mHandler = mHandler;
    }

    @NonNull
    @Override
    public SettingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_setting, parent, false);
        return new SettingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingHolder holder, int position) {
        //setting data
        Setting setting = mData.get(position);

        holder.title.setText(setting.getTitle());
        holder.desc.setText(setting.getDesc());

        Glide.with(mContext)
                .load(setting.getIcon())
                .into(holder.icon);

        //handler
        holder.itemView.setOnClickListener(v -> {
            mHandler.onItemClicked(setting);
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class SettingHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView desc;
        final ImageView icon;

        public SettingHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_list_item_setting_title);
            desc = itemView.findViewById(R.id.textView_list_item_setting_desc);
            icon = itemView.findViewById(R.id.imageView_list_item_setting_icon);
        }
    }

    public interface ClickHandler {
        void onItemClicked(Setting setting);
    }
}
