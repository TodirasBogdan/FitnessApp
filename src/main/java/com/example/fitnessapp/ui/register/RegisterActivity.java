package com.example.fitnessapp.ui.register;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.DBHelper;
import com.example.fitnessapp.MenuActivity;
import com.example.fitnessapp.R;
import com.example.fitnessapp.databinding.ActivityRegisterBinding;
import com.example.fitnessapp.ui.login.LoginActivity;


public class RegisterActivity extends AppCompatActivity {
    private RegisterViewModel registerViewModel;
    private ActivityRegisterBinding binding;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        registerViewModel = new ViewModelProvider(this, new RegisterViewModelFactory())
                .get(RegisterViewModel.class);

        final EditText usernameEditText = binding.usernameRegister;
        final EditText passwordEditText = binding.passwordRegister;
        final EditText ageEditText = binding.age;
        final EditText heightEditText = binding.height;
        final EditText weightEditText = binding.weight;
        final Button registerButton = binding.register;
        DB = new DBHelper(this);

        registerViewModel.getRegisterFormState().observe(this, new Observer<RegisterFormState>() {
            @Override
            public void onChanged(@Nullable RegisterFormState registerFormState) {
                if (registerFormState == null) {
                    return;
                }
                registerButton.setEnabled(registerFormState.isDataValid());
                if (registerFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(registerFormState.getUsernameError()));
                }
                if (registerFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(registerFormState.getPasswordError()));
                }
                if (registerFormState.getAgeError() != null) {
                    ageEditText.setError(getString(registerFormState.getAgeError()));
                }
                if (registerFormState.getHeightError() != null) {
                    heightEditText.setError(getString(registerFormState.getHeightError()));
                }
                if (registerFormState.getWeightError() != null) {
                    weightEditText.setError(getString(registerFormState.getWeightError()));
                }
            }
        });

        registerViewModel.getRegisterResult().observe(this, new Observer<RegisterResult>() {
            @Override
            public void onChanged(@Nullable RegisterResult registerResult) {
                if (registerResult == null) {
                    return;
                }
                if (registerResult.getError() != null) {
                    showRegisterFailed(registerResult.getError());
                }
                if (registerResult.getSuccess() != null) {
                    updateUiWithUser(registerResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy register activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
               registerViewModel.registerDataChanged(usernameEditText.getText().toString(),
                       passwordEditText.getText().toString(), ageEditText.getText().toString(),
                       heightEditText.getText().toString(), weightEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        ageEditText.addTextChangedListener(afterTextChangedListener);
        heightEditText.addTextChangedListener(afterTextChangedListener);
        weightEditText.addTextChangedListener(afterTextChangedListener);

        weightEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    registerViewModel.register(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString(), ageEditText.getText().toString(),
                            heightEditText.getText().toString(), weightEditText.getText().toString());
                }
                return false;
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerViewModel.register(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(), ageEditText.getText().toString(),
                        heightEditText.getText().toString(), weightEditText.getText().toString() );
                if (usernameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUser = DB.checkUsername(usernameEditText.getText().toString());
                    System.out.println("HELLO-----checkuser");
                    if(!checkUser) {
                        System.out.println("HELLO-----insert");
                        Boolean insert = DB.insertDataUser(usernameEditText.getText().toString(), passwordEditText.getText().toString(), ageEditText.getText().toString(),
                                heightEditText.getText().toString(), weightEditText.getText().toString());
                        if (insert) {
                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void updateUiWithUser(RegisteredUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName() + "!!";
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showRegisterFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}