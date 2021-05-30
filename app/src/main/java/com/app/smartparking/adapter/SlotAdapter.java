package com.app.smartparking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.smartparking.R;
import com.app.smartparking.model.Slot;
import com.bumptech.glide.Glide;

import java.util.List;

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.SlotHolder> {

    private final Context mContext;
    private final List<String> mKey;
    private final List<Slot> mData;
    private final ClickHandler mHandler;

    public SlotAdapter(Context mContext, List<String> mKey, List<Slot> mData, ClickHandler mHandler) {
        this.mContext = mContext;
        this.mKey = mKey;
        this.mData = mData;
        this.mHandler = mHandler;
    }

    @NonNull
    @Override
    public SlotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_slot, parent, false);
        return new SlotHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlotHolder holder, int position) {
        String key = mKey.get(position);
        Slot slot = mData.get(position);

        //pengaturan nama
        holder.title.setText(slot.getName());

        //untuk pengaturan slot available dan tidak
        if (slot.isAvailable()) {
            Glide.with(mContext)
                    .load(ContextCompat.getDrawable(mContext, R.drawable.ic_slot_available))
                    .into(holder.icon);
        } else {
            Glide.with(mContext)
                    .load(ContextCompat.getDrawable(mContext, R.drawable.ic_slot_not_available))
                    .into(holder.icon);
        }

        //handler
        holder.itemView.setOnClickListener(v -> {
            mHandler.onItemClicked(key, slot);
        });
    }

    @Override
    public int getItemCount() {
        return mKey.size();
    }

    static class SlotHolder extends RecyclerView.ViewHolder {

        final ImageView icon;
        final TextView title;

        public SlotHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.imageView_list_item_slot_icon);
            title = itemView.findViewById(R.id.textView_list_item_slot_number);
        }
    }

    public interface ClickHandler {
        void onItemClicked(String key, Slot slot);
    }
}
