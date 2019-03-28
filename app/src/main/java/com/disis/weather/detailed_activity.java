package com.disis.weather;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.disis.weather.MOdel.Model;

public class detailed_activity extends AppCompatActivity {
    Model mod;
    String Url_Specification = "https://www.metaweather.com/api/location/";
    String m ="";
    String full ="";
    TextView tvlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_activity);
        tvlocation = findViewById(R.id.tvname);


        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            m = (String) extra.getSerializable("woeid");
            full = Url_Specification+m;

        }
        data();
    }

    private void data() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        //
        progressDialog.setMessage("Fetching");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, full, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                    tvlocation.setText(response);


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
}
