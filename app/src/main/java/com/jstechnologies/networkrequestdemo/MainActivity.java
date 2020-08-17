package com.jstechnologies.networkrequestdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {


    //Controls
    Button btn;
    TextView response;

    //Volley
    RequestQueue mRequestQueue;
    StringRequest mStringRequest;
    String URL="http://192.168.1.14/demoserver/server.php";  // Replace this with the URL of your Server

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialising Controls
        btn=findViewById(R.id.btn);
        response=findViewById(R.id.response);

        //Initialising the Request Queue
        mRequestQueue= Volley.newRequestQueue(this);

        //Setting onClick Event to Send Request
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SendRequest();
            }
        });

    }

    //Method to Send the request
    public void SendRequest()
    {
        mStringRequest= new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String responseString) {
                //This event handles the Response from the Server
                //We've created a String Request which will recieve only response as String
                //After recieving response we need to show the response in the textview .. So,
                response.setText(responseString);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // This event handles the Error from the server
                //If any error happens we need to set that error in the textview too....
                response.setText(error.getMessage());
            }
        });
        //I have forgotten to add this line... please add this to your code....
        //This adds our request to the request Queue and process the Queue
        mRequestQueue.add(mStringRequest);
    }
}