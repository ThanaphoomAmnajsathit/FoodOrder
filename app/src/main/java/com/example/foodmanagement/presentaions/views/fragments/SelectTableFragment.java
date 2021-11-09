package com.example.foodmanagement.presentaions.views.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodmanagement.R;
import com.example.foodmanagement.interfaces.SelectTableInterface;
import com.example.foodmanagement.models.TableData;
import com.example.foodmanagement.presentaions.presenters.SelectTablePresenter;
import com.example.foodmanagement.presentaions.recycleviewadapters.TableRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectTableFragment extends Fragment implements SelectTableInterface.view {

    private ArrayList<TableData> tableData;
    private List<TableData> tablesAdapter;
    private TableRecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private SelectTablePresenter presenter;

    final int sizeOfTable = 14;

    @BindView(R.id.imageview_setting)
    ImageView iv_setting;

    @BindView(R.id.progressBar_selectTable)
    ProgressBar progressBar;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_table, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        init(view);

        //tableAdapter();
        //onTableClick(this);
        onSettingClick(this);
    }

    private void init(View view){
        progressBar.setVisibility(View.VISIBLE);
        tableData = new ArrayList<>();
        presenter = new SelectTablePresenter(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_table);
        GridLayoutManager glm = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(glm);
    }

    public void tableAdapter(){
        presenter.insertTableAdapter(sizeOfTable,tableData);
    }

    public void onSetTables(List<TableData> tables){
        this.tablesAdapter = tables;
        onSetTableAdapter();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSetTableAdapter(){
        Log.d("GetTable", String.valueOf(tablesAdapter));
        adapter = new TableRecyclerAdapter(tablesAdapter,getContext());
        recyclerView.setAdapter(adapter);
        onTableClick(this);
    }

    private void onTableClick(Fragment fragment){
        adapter.setItemClickListener(new TableRecyclerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("onClick","Index = "+position);
                SelectPeopleFragment.newInstance(position+1);
                NavHostFragment.findNavController(fragment)
                        .navigate(R.id.action_SelectTableFragment_to_tableNumberFragment);
            }
        });
    }

    private void onSettingClick(Fragment fragment){
        iv_setting.setOnClickListener(v ->
                NavHostFragment.findNavController(fragment)
                        .navigate(R.id.action_SelectTableFragment_to_settingFragment));
    }
}