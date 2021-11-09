package com.example.foodmanagement.presentaions.recycleviewadapters;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmanagement.R;
import com.example.foodmanagement.models.TableData;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class TableRecyclerAdapter extends RecyclerView.Adapter<TableRecyclerAdapter.Holder> {

    private final List<TableData> tableDataSet;
    private final Context context;
    static ItemClickListener mListener;

    public TableRecyclerAdapter(List<TableData> tableData , Context context){
        this.tableDataSet = tableData;
        this.context = context;
    }

    public interface ItemClickListener{
        void onItemClick(int position);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.mListener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_table_list_item,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        TableData currentItem = tableDataSet.get(position);
        if (currentItem.isBusy().equals("true")){
            holder.mcv_table.setStrokeColor(Color.parseColor("#F20000"));
            holder.mcv_table.setStrokeWidth((int)convertDpToPixel(4f,context));
        }
        holder.tv_tableNumber.setText("โต๊ะ "+currentItem.getTableNumber());
        holder.tv_people.setText(currentItem.getPeople());
    }

    @Override
    public int getItemCount() {
        return tableDataSet.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        MaterialCardView mcv_table;
        TextView tv_tableNumber;
        TextView tv_people;
        ImageView iv_human;
        boolean table_available = true;

        public Holder(View itemView){
            super(itemView);
            mcv_table = (MaterialCardView) itemView.findViewById(R.id.cardview_table);
            tv_tableNumber = (TextView) itemView.findViewById(R.id.textview_table);
            tv_people = (TextView) itemView.findViewById(R.id.textview_people);
            iv_human = (ImageView) itemView.findViewById(R.id.imageview_human);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null){
                MaterialCardView view = v.findViewById(R.id.cardview_table);
                if (view.getStrokeColor() != Color.parseColor("#F20000")) {
                    mListener.onItemClick(getAdapterPosition());
                    if (table_available){
                        table_available = false;
                        mcv_table.setStrokeColor(context.getResources().getColor(R.color.orange));
                        mcv_table.setStrokeWidth((int)convertDpToPixel(5f,context));
                    }else {
                        table_available = true;
                        mcv_table.setStrokeColor(context.getResources().getColor(R.color.stroke_card_view));
                        mcv_table.setStrokeWidth((int)convertDpToPixel(3f,context));
                    }
                }
            }
        }
    }

    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
