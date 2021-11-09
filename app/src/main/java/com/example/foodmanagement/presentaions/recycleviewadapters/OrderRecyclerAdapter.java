package com.example.foodmanagement.presentaions.recycleviewadapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmanagement.R;
import com.example.foodmanagement.models.OrderListData;
import com.example.foodmanagement.presentaions.views.activities.MainMenu;

import java.util.ArrayList;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.Holder> {

    private Context context;
    private ArrayList<OrderListData> orderListDataSet;
    private MainMenu mainMenu;

    ItemClickListener mListener;

    public interface ItemClickListener{
        void onItemClick(int position);
    }

    public void setItemClickListener(ItemClickListener listener){
        mListener = listener;
    }

    public OrderRecyclerAdapter(Context context, ArrayList<OrderListData> orderListDataSet){
        this.context = context;
        this.orderListDataSet = orderListDataSet;
        mainMenu = new MainMenu();
    }

    public OrderRecyclerAdapter(){}

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_list_item,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        OrderListData currentItem = orderListDataSet.get(position);
        Log.d("CURRENTITEM",currentItem.toString());

            if (MainMenu.getSentOrder()) {
                holder.cv_order.setCardBackgroundColor(Color.parseColor("#CAF4CE"));
                holder.iv_icon_correct.setVisibility(View.VISIBLE);
                holder.iv_delete.setVisibility(View.INVISIBLE);
            }

        if (currentItem.getOrder_modify() != null){
            if (currentItem.getOrder_modify().equals("null")){
                holder.tv_modify_title.setVisibility(View.GONE);
                holder.tv_modify_box.setVisibility(View.GONE);
            }else {
                holder.tv_modify_title.setVisibility(View.VISIBLE);
                holder.tv_modify_box.setVisibility(View.VISIBLE);
                holder.tv_modify_box.setText(currentItem.getOrder_modify());
            }
        }else {
            holder.tv_modify_title.setVisibility(View.GONE);
            holder.tv_modify_box.setVisibility(View.GONE);
        }
        if (currentItem.getOrder_takeAway() != null) {
            if (currentItem.getOrder_takeAway().equals("1")) {
                holder.iv_icon_takeAway.setVisibility(View.VISIBLE);
            } else {
                holder.iv_icon_takeAway.setVisibility(View.GONE);
            }
        }else {
            holder.iv_icon_takeAway.setVisibility(View.GONE);
        }
        holder.tv_order_foodName.setText(currentItem.getOrder_foodName());
        holder.tv_order_price.setText(currentItem.getOrder_price());
        holder.tv_order_date.setText(currentItem.getOrder_date());
        holder.tv_order_userName.setText(currentItem.getOrder_userName());
        holder.tv_order_sumFood.setText(currentItem.getOrder_plusCount());
    }

    @Override
    public int getItemCount() {
        return orderListDataSet.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_order_foodName;
        TextView tv_order_price;
        TextView tv_order_date;
        TextView tv_order_userName;
        ImageView iv_icon_correct;
        ImageView iv_icon_takeAway;
        ImageView iv_delete;
        CardView cv_order;
        TextView tv_modify_title;
        TextView tv_modify_box;
        TextView tv_order_sumFood;

        public Holder(View itemView){
            super(itemView);
            tv_order_foodName = itemView.findViewById(R.id.textView_order_FoodName);
            tv_order_price = itemView.findViewById(R.id.textView_order_price);
            tv_order_date = itemView.findViewById(R.id.textview_order_date);
            tv_order_userName = itemView.findViewById(R.id.textview_order_username);
            iv_icon_correct = itemView.findViewById(R.id.imageView_correct);
            iv_icon_takeAway = itemView.findViewById(R.id.imageView_takeAway);
            iv_delete = itemView.findViewById(R.id.imageView_delete);
            cv_order = itemView.findViewById(R.id.cardview_order);
            tv_modify_title = itemView.findViewById(R.id.textview_modify_title);
            tv_modify_box = itemView.findViewById(R.id.textview_modify_box);
            tv_order_sumFood = itemView.findViewById(R.id.textview_order_sumFood);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null){
                mListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
