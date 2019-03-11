package com.example.webservicetugas;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JadwalSholat extends AppCompatActivity  {
    private static final String URL_DATA = "http://192.168.43.177/webservices/jadwalSholat.json";
    ArrayList<JadwalSholatModel> jadwalSholatModels;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    public JadwalSholatAdapter jadwalSholatAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwalsholat);
        recyclerView = findViewById(R.id.rv2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadProducts();

        jadwalSholatAdapter = new JadwalSholatAdapter(getApplicationContext(), jadwalSholatModels);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(JadwalSholat.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(jadwalSholatAdapter);
    }

    private void loadProducts() {
        jadwalSholatModels = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject jadwalSholat = array.getJSONObject(i);

                                //adding the product to product list
                                jadwalSholatModels.add(new JadwalSholatModel(
                                        jadwalSholat.getString("kota"),
                                        jadwalSholat.getString("subuh"),
                                        jadwalSholat.getString("dhuhur"),
                                        jadwalSholat.getString("ashar"),
                                        jadwalSholat.getString("maghrib"),
                                        jadwalSholat.getString("isya")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            JadwalSholatAdapter adapter = new JadwalSholatAdapter(JadwalSholat.this, jadwalSholatModels);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
