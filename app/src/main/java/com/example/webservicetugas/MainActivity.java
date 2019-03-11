package com.example.webservicetugas;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String URL_DATA = "https://al-quran-8d642.firebaseio.com/data.json?print=pretty";
    private ArrayList<ListSurahModel> listSurahModels;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    public ListSurahAdapter listSurahAdapter;
    private SearchView searchView;
    private Button btnStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listSurahAdapter = new ListSurahAdapter(MainActivity.this, listSurahModels);
        listSurahModels = new ArrayList<>();



        btnStock = (Button)findViewById(R.id.btn_jadwalSholat);
        btnStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveIntent = new Intent(MainActivity.this, JadwalSholat.class);
                startActivity(moveIntent);
            }
        });

        loadProducts();
    }

    private void loadProducts() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data..");
        progressDialog.show();
        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            //converting the string to json array object
                            //JSONObject jsonObject;
                            //jsonObject = new JSONObject(response);
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject listsurah = array.getJSONObject(i);

                                //adding the product to product list
                                listSurahModels.add(new ListSurahModel(
                                        listsurah.getString("nama"),
                                        listsurah.getString("nomor"),
                                        listsurah.getString("arti")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            ListSurahAdapter adapter = new ListSurahAdapter(MainActivity.this, listSurahModels);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                    }
                });

        //adding our stringrequest to queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}

