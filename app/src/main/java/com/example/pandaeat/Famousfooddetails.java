package com.example.pandaeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class Famousfooddetails extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_famousfooddetails);
        listView=findViewById(R.id.famousfoodlistview);






        // textView=findViewById(R.id.text1);
        // button=findViewById(R.id.button);

        // button.setOnClickListener(new View.OnClickListener() {
        //      @Override
        //     public void onClick(View view) {

        //        // to do something
        parseData();

        //    }
        //  });

    }

    public void parseData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://api.myjson.online/v1/records/e2867697-1451-493c-8734-67acd42bb448";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Json parsing
                jsonParse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //error messeage
                // textView.setText("Not working !");
                error.getStackTrace();
            }
        });

        requestQueue.add(stringRequest);


    }

    public void jsonParse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            ArrayList<String> names =new ArrayList<String>();
            ArrayList<String> ages = new ArrayList<String>();
            ArrayList<String> cities = new ArrayList<String>();



            //data ta holo key
            for(int i=0; i<jsonArray.length(); i++)
            {
                JSONObject jsonObject2= jsonArray.getJSONObject(i);
                String jname,jcity, jage;
                jname = jsonObject2.getString("name");
                names.add(jname);
                jage = jsonObject2.getString("age");
                jcity = jsonObject2.getString("city");
                ages.add(jage);
                cities.add(jcity);

//                textView.append("Name : "+jname+", Age : "+jage+", City : "+jcity+"\n\n" );




            }

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, names);
            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(Famousfooddetails.this, "Clicked on  : "+names.get(i), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Famousfooddetails.this, showfamousfoodactivity.class);
                    String city1 = cities.get(i);
                    String age1 = ages.get(i);
                    String name1=names.get(i);

                    intent.putExtra("name", name1);
                    intent.putExtra("age", age1);
                    intent.putExtra("city", city1);

                    startActivity(intent);





                }
            });

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}