package com.disis.weather;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String Url_Data ="https://www.metaweather.com/api/location/search/?query=";
    EditText etinput;
    RecyclerView recyclerView;
    String p ="";
    private List<Model> modelList;
    Adapter adapter;
    int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etinput = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        Log.i("recycle","e1");
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        modelList = new ArrayList<>();

    }

    private void load() {
        modelList.clear();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        //
        progressDialog.setMessage("Fetching");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, p, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        modelList.add(new Model(jsonObject.getString("title"),
                                jsonObject.getInt("woeid")));
                        Log.i("zvalue",jsonObject.getString("title"));
                    }
                    adapter = new Adapter(MainActivity.this,modelList);
                    Log.i("Tag","ok");
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void add(View view) {
        //if(count>0)
          //  adapter.clearApplication();

        String s = etinput.getText().toString();
        Log.i("uiiii",s);
        if(s==null || s.equals("")){
            Toast.makeText(getApplicationContext(),"Enter a city name",Toast.LENGTH_LONG).show();
        }
        else {
            p = Url_Data + s;
            load();
        }
    }
}
