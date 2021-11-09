package com.example.foodmanagement.interfaces;

import com.example.foodmanagement.models.TableData;

import java.util.ArrayList;
import java.util.List;

public interface SelectTableInterface {
    interface view{

        void onSetTableAdapter();

        void onSetTables(List<TableData> tables);

    }

    interface presenter{

        void insertTableAdapter(int sizeOfTable , ArrayList<TableData> tableData);

    }
}
