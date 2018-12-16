package com.example.meita.tickercode;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.meita.tickercode.adapter.DataAdapter;
import com.example.meita.tickercode.model.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String URL_DATA = "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=jakarta&apikey=OEE4T23F88VXPSNX";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<DataModel> dataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataModels = new ArrayList<>();
        loadData();

    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("bestMatches");

                    for (int i = 0; i < array.length(); i++){

                        JSONObject object = array.getJSONObject(i);
                        //DataModel data = new DataModel(object.getString("1. symbol"), object.getString("2. name"), object.getString("4. region"), object.getString("9. matchScore"));
                        DataModel data = new DataModel();
                        data.setSymbol(object.getString("1. symbol"));
                        data.setName(object.getString("2. name"));
                        data.setRegion(object.getString("4. region"));
                        data.setMatchScore(object.getString("9. matchScore"));
                        dataModels.add(data);

                    }

                    //adapter = new DevelopersAdapter(developersLists, getApplicationContext());
                    adapter = new DataAdapter(dataModels, getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
