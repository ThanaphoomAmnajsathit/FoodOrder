package com.example.foodmanagement.presentaions.presenters;

import com.example.foodmanagement.domain.repositories.UserRepository;
import com.example.foodmanagement.domain.services.MainServices;
import com.example.foodmanagement.domain.usecase.GetTablesUseCase;
import com.example.foodmanagement.domain.usecase.GetUserUseCase;
import com.example.foodmanagement.interfaces.SelectTableInterface;
import com.example.foodmanagement.models.TableData;

import java.util.ArrayList;
import java.util.List;

public class SelectTablePresenter implements SelectTableInterface.presenter {

    private SelectTableInterface.view view;

    public SelectTablePresenter(SelectTableInterface.view view){
        this.view = view;
        UserRepository repository = new MainServices().getRetrofit().create(UserRepository.class);
        GetTablesUseCase getTablesUseCase = new GetTablesUseCase(repository,this);
        getTablesUseCase.execute(null);
    }

    @Override
    public void insertTableAdapter(int sizeOfTable, ArrayList<TableData> tableData){
      /*  for (int position = 0; position <= sizeOfTable ; position++) {
            final int tableNumber = position+1;
            tableData.add(position,new TableData("Table "+tableNumber,"0","false"));
        }
        tableData.get(2).setBusy("true");
        tableData.get(3).setBusy("true");
        tableData.get(6).setBusy("true");
        tableData.get(8).setBusy("true");
        tableData.get(10).setBusy("true");

        tableData.get(2).setPeople("2");
        tableData.get(3).setPeople("1");
        tableData.get(6).setPeople("6");
        tableData.get(8).setPeople("4");
        tableData.get(10).setPeople("3");
        view.onSetTableAdapter(tableData);*/
    }

    public void onSetTables(List<TableData> tables){
        view.onSetTables(tables);
    }

    public void onError(){}
}
