package com.app.smartparking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.smartparking.R;
import com.app.smartparking.base.Constant;
import com.app.smartparking.model.Order;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {

    private final Context mContext;
    private final List<String> mKey;
    private final List<Order> mData;
    private final View mEmptyView;
    private final ClickHandler mHandler;

    public OrderAdapter(Context mContext, List<String> mKey, List<Order> mData, View mEmptyView, ClickHandler mHandler) {
        this.mContext = mContext;
        this.mKey = mKey;
        this.mData = mData;
        this.mEmptyView = mEmptyView;
        this.mHandler = mHandler;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_order, parent, false);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        String key = mKey.get(position);
        Order order = mData.get(position);

        holder.slot.setText(order.getSlot().getName());
        holder.code.setText(key);
        holder.date.setText(longToDate(order.getDateTime()));

        //handler
        holder.itemView.setOnClickListener(v -> {
            mHandler.onItemClicked(key, order);
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private String longToDate(long timeInMillis) {
        //convert long time to string date
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.DATETIME_FORMAT, Locale.getDefault());
        return simpleDateFormat.format(calendar.getTime());
    }

    public void updateView() {
        if (mData.size() == 0)
            mEmptyView.setVisibility(View.VISIBLE);
        else
            mEmptyView.setVisibility(View.GONE);
    }

    static class OrderHolder extends RecyclerView.ViewHolder {

        final TextView slot;
        final TextView code;
        final TextView date;

        public OrderHolder(@NonNull View itemView) {
            super(itemView);

            slot = itemView.findViewById(R.id.textView_list_item_order_slot_name);
            code = itemView.findViewById(R.id.textView_list_item_order_code);
            date = itemView.findViewById(R.id.textView_list_item_order_date);
        }
    }

    public interface ClickHandler {
        void onItemClicked(String key, Order order);
    }
}
