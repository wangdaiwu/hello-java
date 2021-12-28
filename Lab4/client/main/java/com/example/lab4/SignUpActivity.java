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
import java.util.regex.Pattern;

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

    private boolean checkUsername() {
        String text = usernameEditText.getText().toString();
        int length = text.length();
        String pattern1 = "^[a-zA-Z].*$";
        String pattern2 = "^[a-zA-Z][\\w]*$";
        String pattern3 = ".*[A-Z].*";
        boolean hasError = false;
        String tips = "";
        if (length < 5 || length > 10) {
            hasError = true;
            tips += getString(R.string.username_error1) + "\n";
        } else {
            tips += getString(R.string.username_correct1) + "\n";
        }

        if (!Pattern.matches(pattern1, text)) {
            hasError = true;
            tips += getString(R.string.username_error2) + "\n";
        } else {
            tips += getString(R.string.username_correct2) + "\n";
        }

        if (!Pattern.matches(pattern2, text)) {
            hasError = true;
            tips += getString(R.string.username_error3) + "\n";
        } else {
            tips += getString(R.string.username_correct3) + "\n";
        }

        if (!Pattern.matches(pattern3, text)) {
            hasError = true;
            tips += getString(R.string.username_error4);
        } else {
            tips += getString(R.string.username_correct4);
        }

        if (hasError) {
            usernameLayout.setError(tips);
        } else {
            usernameLayout.setHelperText(tips);
        }
        return hasError;
    }

    private boolean checkPassword() {
        String text = passwordEditText.getText().toString();
        int length = text.length();
        String pattern = "^[\\w]*$";
        boolean hasError = false;
        String tips = "";
        if (length < 6 || length > 12) {
            hasError = true;
            tips += getString(R.string.password_error1) + "\n";
        } else {
            tips += getString(R.string.password_correct1) + "\n";
        }
        if (!Pattern.matches(pattern, text)) {
            hasError = true;
            tips += getString(R.string.password_error2);
        } else {
            tips += getString(R.string.password_correct2);
        }

        if (hasError) {
            passwordLayout.setError(tips);
        } else {
            passwordLayout.setHelperText(tips);
        }
        return hasError;
    }

    private boolean checkConfirmPassword() {
        boolean hasError = false;
        String tips = "";
        if (!confirmPasswordEditText.getText().toString().equals(passwordEditText.getText().toString())) {
            hasError = true;
            tips += getString(R.string.passwords_do_not_match);
        } else {
            tips += getString(R.string.passwords_match);
        }

        if (hasError) {
            confirmPasswordLayout.setError(tips);
        } else {
            confirmPasswordLayout.setHelperText(tips);
        }
        return hasError;
    }

    private boolean checkName() {
        String text = nameEditText.getText().toString();
        int length = text.length();
        boolean hasError = false;
        String tips = "";
        if (length == 0 || length > 20) {
            hasError = true;
            tips += getString(R.string.name_error);
        } else {
            tips += getString(R.string.name_correct);
        }

        if (hasError) {
            nameLayout.setError(tips);
        } else {
            nameLayout.setHelperText(tips);
        }
        return hasError;
    }

    private boolean checkTelephone() {
        boolean hasError = false;
        String tips = "";
        if (telephoneEditText.getText().toString().length() != 11) {
            hasError = true;
            tips += getString(R.string.telephone_numbers_error);
        } else {
            tips += getString(R.string.telephone_numbers_correct);
        }

        if (hasError) {
            telephoneLayout.setError(tips);
        } else {
            telephoneLayout.setHelperText(tips);
        }
        return hasError;
    }

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
                checkUsername();
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
                checkPassword();
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
                checkConfirmPassword();
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
                checkName();
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
                checkTelephone();
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
        boolean isValid = false;
        if (checkUsername()) {
            Toast.makeText(SignUpActivity.this, getString(R.string.invalid_username), Toast.LENGTH_SHORT).show();
        } else if (checkPassword()) {
            Toast.makeText(SignUpActivity.this, getString(R.string.invalid_password), Toast.LENGTH_SHORT).show();
        } else if (checkConfirmPassword()) {
            Toast.makeText(SignUpActivity.this, getString(R.string.invalid_confirm_password), Toast.LENGTH_SHORT).show();
        } else if (checkName()) {
            Toast.makeText(SignUpActivity.this, getString(R.string.invalid_name), Toast.LENGTH_SHORT).show();
        } else if (checkTelephone()) {
            Toast.makeText(SignUpActivity.this, getString(R.string.invalid_telephone_numbers), Toast.LENGTH_SHORT).show();
        } else {
            isValid = true;
        }
        return isValid;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void cancel(View view) {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void createAccount(View view) {
        final String TAG = "signUp";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.cancelAll(TAG);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http://1.14.248.67:8080/Lab4/labServlet?action=signUp",
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
                                    finish();
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