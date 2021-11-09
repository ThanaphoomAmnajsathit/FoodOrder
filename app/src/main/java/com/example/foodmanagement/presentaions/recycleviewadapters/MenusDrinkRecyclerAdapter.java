package com.example.foodmanagement.presentaions.recycleviewadapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmanagement.R;
import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.domain.services.MainServices;
import com.example.foodmanagement.domain.usecase.GetProductUseCase;
import com.example.foodmanagement.models.Product;
import com.example.foodmanagement.presentaions.presenters.DrinkMenusPresenter;
import com.example.foodmanagement.presentaions.presenters.FoodMenusPresenter;

import java.util.List;

public class MenusDrinkRecyclerAdapter extends RecyclerView.Adapter<MenusDrinkRecyclerAdapter.Holder> implements Filterable {
    private final List<Product> menusDataSet;
    private FoodMenusPresenter foodPresenter;
    private DrinkMenusPresenter drinkPresenter;
    public static boolean isClickable = true;
    static MenusRecyclerAdapter.ItemClickListener mListener;

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public void setItemClickListener(MenusRecyclerAdapter.ItemClickListener listener) {
        this.mListener = listener;
    }

    public MenusDrinkRecyclerAdapter(List<Product> menusListData, DrinkMenusPresenter drinkPresenter) {
        this.menusDataSet = menusListData;
        this.drinkPresenter = drinkPresenter;
    }

    @NonNull
    @Override
    public MenusDrinkRecyclerAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menus_list_item, parent, false);
        MenusDrinkRecyclerAdapter.Holder holder = new MenusDrinkRecyclerAdapter.Holder(view);
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.setIsRecyclable(false);
        Product currentItem = menusDataSet.get(position);
        holder.tv_id.setText(currentItem.getMenu_id());
        holder.tv_price.setText(currentItem.getMenu_price());
        holder.tv_menus.setText(currentItem.getMenu_name());
    }

    @Override
    public void onViewAttachedToWindow(MenusDrinkRecyclerAdapter.Holder viewHolder){
        super.onViewAttachedToWindow(viewHolder);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return menusDataSet.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            if (charSequence.toString().isEmpty()) {

            } else {
                UserRepository repository = new MainServices().getRetrofit().create(UserRepository.class);
                GetProductUseCase getProductUseCase = new GetProductUseCase(repository, foodPresenter, null, String.valueOf(charSequence), true);
                getProductUseCase.execute(null);

                /*for (User user: usersAll){
                    if (user.toString().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filterdList.add(user);
                    }
                }*/
            }

            /*FilterResults filterResults = new FilterResults();
            filterResults.values = filterdList;*/
            return null;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            menusDataSet.clear();
            //users.addAll((Collection<? extends User>) filterResults.values);
            notifyDataSetChanged();
        }
    };


    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cardView;
        LinearLayout linearLayout;
        TextView tv_id;
        TextView tv_price;
        TextView tv_menus;


        public Holder(View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_menu_detail);
            tv_id = itemView.findViewById(R.id.textview_idfood);
            tv_price = itemView.findViewById(R.id.textView_price);
            tv_menus = itemView.findViewById(R.id.textView_foodName);
            linearLayout = itemView.findViewById(R.id.linearlayout_recyclerview_menus);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener!=null){
                if (isClickable) {
                    mListener.onItemClick(getAdapterPosition());
                }
            }
        }
    }
}
