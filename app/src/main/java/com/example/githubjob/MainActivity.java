package com.example.githubjob;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String URL = "https://jobs.github.com/positions.json?description=java&full_time=true&location=sf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        mActivity = MainActivity.this;

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String lat = ((EditText)findViewById(R.id.lat)).getText().toString();
                if (lat.length() == 0) {
                    lat = "37.3229978";
                }
                String lon = ((EditText)findViewById(R.id.lon)).getText().toString();
                if (lon.length() == 0) {
                    lon = "-122.0321823";
                }
                String topic = ((EditText)findViewById(R.id.topic)).getText().toString();
                if (topic.length() == 0) {
                    topic = "java";
                }

                URL = "https://jobs.github.com/positions.json?description="
                        + topic
                        + "&lat="
                        + lat
                        + "&lon="
                        + lon;
                Log.e("URL", URL);
                RequestQueue queue = Volley.newRequestQueue(mContext);
                getJobList(URL, queue, new VolleyCallBack());

            }
        });
    }

    private void showRecyclerView(List<JobItem> jobs) {
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(jobs);
        recyclerView.setAdapter(mAdapter);
    }

    public void getJobList(String url, RequestQueue queue, final VolleyCallBack callBack) {

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            Log.e("TAGResponse", String.valueOf(response));
                            callBack.onSuccess(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i < response.length(); i++) {
//                            try {
//                                Log.e("TAGResponse", response.getJSONObject(i).getString("company"));
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("RESPONSEERROR", String.valueOf(error));
                    }
                });

        queue.add(arrayRequest);
    }
    class VolleyCallBack {
        void onSuccess(JSONArray array) throws JSONException {
            List<JobItem> jobs = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject cur = array.getJSONObject(i);
                    JobItem item = new JobItem(cur);
                    Log.e("jobitem", cur.getString("company"));
                    jobs.add(item);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            showRecyclerView(jobs);
        }
    }
}
