package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WelcomeActivity extends AppCompatActivity {

    private TextView usernameTextView;
    private TextView nameTextView;
    private TextView ageTextView;
    private TextView telephoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
        welcome();
    }

    private void init() {
        usernameTextView = findViewById(R.id.username_show);
        nameTextView = findViewById(R.id.name_show);
        ageTextView = findViewById(R.id.age_show);
        telephoneTextView = findViewById(R.id.telephone_show);
    }

    private long lastBackPressTime = -1;

    @Override
    public void onBackPressed() {
        long currentBackPressTime = System.currentTimeMillis();
        if (lastBackPressTime == -1 ||
                currentBackPressTime - lastBackPressTime > 2000) {
            Toast.makeText(WelcomeActivity.this, getString(R.string.quit_app), Toast.LENGTH_SHORT).show();
        } else {
            finish();
        }
        lastBackPressTime = currentBackPressTime;
    }

    public void signOut(View view) {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void welcome() {
        final String TAG = "welcome";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.cancelAll(TAG);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http://xxx:8080/Lab4/welcome",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            usernameTextView.setText(jsonObject.getString("username"));
                            nameTextView.setText(jsonObject.getString("name"));
                            ageTextView.setText(jsonObject.getString("age"));
                            telephoneTextView.setText(jsonObject.getString("phone"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                usernameTextView.setText("That didn't work!\n" + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                map.put("username", getIntent().getStringExtra("username"));
                return map;
            }
        };
        stringRequest.setTag(TAG);
        requestQueue.add(stringRequest);
    }
}