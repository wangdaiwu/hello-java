package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private TextView usernameEditText;
    private TextView passwordEditText;
    private TextView confirmPasswordEditText;
    private TextView nameEditText;
    private TextView ageEditText;
    private TextView telephoneEditText;

    private TextInputLayout usernameLayout;
    private TextInputLayout passwordLayout;
    private TextInputLayout confirmPasswordLayout;
    private TextInputLayout nameLayout;
    private TextInputLayout telephoneLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();

        usernameLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (usernameEditText.getText().toString().length() > 10) {
                    usernameLayout.setError(getString(R.string.length_limit_exceeded));
                } else {
                    usernameLayout.setError(null);
                }
            }
        });

        passwordLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (passwordEditText.getText().toString().length() > 8) {
                    passwordLayout.setError(getString(R.string.length_limit_exceeded));
                } else {
                    passwordLayout.setError(null);
                }
            }
        });

        confirmPasswordLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!confirmPasswordEditText.getText().toString().equals(passwordEditText.getText().toString())) {
                    confirmPasswordLayout.setError(getString(R.string.passwords_do_not_match));
                } else {
                    confirmPasswordLayout.setError(null);
                }
            }
        });

        nameLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (nameEditText.getText().toString().length() > 20) {
                    nameLayout.setError(getString(R.string.length_limit_exceeded));
                } else {
                    nameLayout.setError(null);
                }
            }
        });

        telephoneLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (telephoneEditText.getText().toString().length() != 11) {
                    telephoneLayout.setError(getString(R.string.invalid_telephone_numbers));
                } else {
                    telephoneLayout.setError(null);
                }
            }
        });
    }

    private void init() {
        usernameEditText = findViewById(R.id.username_sign_up);
        passwordEditText = findViewById(R.id.password_sign_up);
        confirmPasswordEditText = findViewById(R.id.confirm_password_sign_up);
        nameEditText = findViewById(R.id.name_sign_up);
        ageEditText = findViewById(R.id.age_sign_up);
        telephoneEditText = findViewById(R.id.telephone_sign_up);

        usernameLayout = findViewById(R.id.username_layout_sign_up);
        passwordLayout = findViewById(R.id.password_layout_sign_up);
        confirmPasswordLayout = findViewById(R.id.confirm_password_layout_sign_up);
        nameLayout = findViewById(R.id.name_layout_sign_up);
        telephoneLayout = findViewById(R.id.telephone_layout_sign_up);
    }

    private boolean isEditTextValid() {
        if (usernameEditText.getText().toString().isEmpty()) {
            Toast.makeText(SignUpActivity.this, "username is required", Toast.LENGTH_SHORT).show();
            usernameLayout.setError(getString(R.string.required));
            return false;
        } else if (usernameEditText.getText().toString().length() > 10) {
            Toast.makeText(SignUpActivity.this, "username " + getString(R.string.length_limit_exceeded), Toast.LENGTH_SHORT).show();
            usernameLayout.setError(getString(R.string.length_limit_exceeded));
            return false;
        } else if (passwordEditText.getText().toString().isEmpty()) {
            Toast.makeText(SignUpActivity.this, "password is required", Toast.LENGTH_SHORT).show();
            passwordLayout.setError(getString(R.string.required));
            return false;
        } else if (passwordEditText.getText().toString().length() > 8) {
            Toast.makeText(SignUpActivity.this, "password " + getString(R.string.length_limit_exceeded), Toast.LENGTH_SHORT).show();
            passwordLayout.setError(getString(R.string.length_limit_exceeded));
            return false;
        } else if (!confirmPasswordEditText.getText().toString().equals(passwordEditText.getText().toString())) {
            Toast.makeText(SignUpActivity.this, "confirm password " + getString(R.string.passwords_do_not_match), Toast.LENGTH_SHORT).show();
            confirmPasswordLayout.setError(getString(R.string.passwords_do_not_match));
            return false;
        } else if (nameEditText.getText().toString().isEmpty()) {
            Toast.makeText(SignUpActivity.this, "name is required", Toast.LENGTH_SHORT).show();
            nameLayout.setError(getString(R.string.required));
            return false;
        } else if (nameEditText.getText().toString().length() > 20) {
            Toast.makeText(SignUpActivity.this, "name " + getString(R.string.length_limit_exceeded), Toast.LENGTH_SHORT).show();
            nameLayout.setError(getString(R.string.length_limit_exceeded));
            return false;
        } else if (telephoneEditText.getText().toString().length() != 11) {
            Toast.makeText(SignUpActivity.this, "telephone " + getString(R.string.invalid_telephone_numbers), Toast.LENGTH_SHORT).show();
            telephoneLayout.setError(getString(R.string.invalid_telephone_numbers));
            return false;
        }
        return true;
    }

    public void cancel(View view) {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void createAccount(View view) {
        final String TAG = "signUp";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.cancelAll(TAG);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http://47.107.93.192:8080/Lab4/labServlet?action=signUp",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);

                            if (isEditTextValid()) {
                                if (jsonObject.getString("result").equals("success")) {
                                    Intent intent = new Intent(SignUpActivity.this, WelcomeActivity.class);
                                    intent.putExtra("username", usernameEditText.getText().toString());
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(SignUpActivity.this, jsonObject.getString("result"), Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                map.put("username", usernameEditText.getText().toString());
                map.put("password", passwordEditText.getText().toString());
                map.put("name", nameEditText.getText().toString());
                map.put("age", ageEditText.getText().toString());
                map.put("teleno", telephoneEditText.getText().toString());
                return map;
            }
        };
        stringRequest.setTag(TAG);
        requestQueue.add(stringRequest);
    }
}
