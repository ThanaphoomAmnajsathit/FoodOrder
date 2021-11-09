package com.example.foodmanagement.domain.repositories;

import com.example.foodmanagement.models.DeleteOrders;
import com.example.foodmanagement.models.InsertOrderList;
import com.example.foodmanagement.models.InsertUser;
import com.example.foodmanagement.models.ReturnMyBill;
import com.example.foodmanagement.models.ReturnMyBillCount;
import com.example.foodmanagement.models.ReturnProducts;
import com.example.foodmanagement.models.ReturnTables;
import com.example.foodmanagement.models.ReturnUsers;
import com.example.foodmanagement.models.StatusTable;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserRepository {

    @GET("/GetUsers.php")
    Observable<Response<ReturnUsers>> getUser(@Query("displayname") String displayname);

    @GET("/GetTables.php")
    Observable<Response<ReturnTables>> getTable();

    //@GET("/food_order/GetProduct.php")
    @GET("/GetProduct.php")
    Observable<Response<ReturnProducts>> getProduct(@Query("categoryid") String categoryid);

    //@GET("/food_order/GetProduct.php")
    @GET("/GetProduct.php")
    Observable<Response<ReturnProducts>> searchProduct(@Query("search") String search);

    //@GET("/food_order/GetMyBill.php")
    @GET("/GetMyBill.php")
    Observable<Response<ReturnMyBill>> getMyBill(@Query("user_id") String user_id);

    //@GET("/food_order/GetMyBillRow.php")
    @GET("/GetMyBillRow.php")
    Observable<Response<ReturnMyBillCount>> getMyBillCount();

    //@POST("/food_order/InsertOrder.php")
    @POST("/InsertOrder.php")
    Call<InsertOrderList> PostOrder(@Body InsertOrderList insertUser);

    @POST("/InsertUser.php")
    Call<InsertUser> PostUser(@Body InsertUser InsertUser);

    @POST("/UpdateTable.php")
    Call<StatusTable> PostTable(@Body StatusTable statusTable);

    //@HTTP(method = "DELETE", path = "/food_order/DeleteOrders.php", hasBody = true)
    //@HTTP(method = "DELETE", path = "/food_order_api/DeleteOrders.php", hasBody = true)
    @POST("/DeleteOrders.php")
    Call<DeleteOrders> deleteOrders(@Body DeleteOrders deleteUser);
}
