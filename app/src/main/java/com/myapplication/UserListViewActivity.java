package com.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class UserListViewActivity extends AppCompatActivity {

    ListView listView;
    GridView gridView;
    Button change;

    Spinner spinner;
    UserSpinnerAdapter userSpinnerAdapter;

    UserListAdapter adapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlistview_layout);
        listView = findViewById(R.id.listview);
        gridView = findViewById(R.id.gridview);

        spinner = findViewById(R.id.spinner);

        databaseHelper = new DatabaseHelper(this);
        adapter = new UserListAdapter(this, databaseHelper.getUserList());

        userSpinnerAdapter = new UserSpinnerAdapter(this,databaseHelper.getUserList());

//        listView.setAdapter(adapter);
        gridView.setAdapter(adapter);

        spinner.setAdapter(userSpinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Userinfo info = (Userinfo) spinner.getSelectedItem();
                Toast.makeText(UserListViewActivity.this, "Name:"+info.firstname, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        change = findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listView.getVisibility() == View.VISIBLE) {
                    listView.setVisibility(View.GONE);
                    gridView.setVisibility(View.VISIBLE);
                } else {
                    listView.setVisibility(View.VISIBLE);
                    gridView.setVisibility(View.GONE);
                }
            }
        });

        fetchData();

    }

    public void fetchData(){
 // ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://softechnepal.com/exam/service.php?task=getStudent";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i("response",response);
                        Toast.makeText(UserListViewActivity.this, "Response:"+response, Toast.LENGTH_SHORT).show();
                        parseData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void parseData(String data){
        try {
            JSONObject object = new JSONObject(data);
            JSONArray result = object.getJSONArray("result");
            ArrayList<Userinfo> list = new ArrayList<>();
            for (int i = 0 ; i<result.length();i++){
                Userinfo info = new Userinfo();
                JSONObject obj = result.getJSONObject(i);
               info.id= obj.getString("id");
               info.firstname= obj.getString("name");
               info.email= obj.getString("mobile");
               info.address= obj.getString("address");
               list.add(info);
            }
            displayUsers(list);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    public void displayUsers(ArrayList<Userinfo> list){
    UserListAdapter adapter1 = new UserListAdapter(this,list);
    listView.setAdapter(adapter1);


    }
}

