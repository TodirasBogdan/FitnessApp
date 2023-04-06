package com.example.fitnessapp.ui.register;

import android.util.Patterns;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fitnessapp.R;
import com.example.fitnessapp.data.RegisterRepository;
import com.example.fitnessapp.data.Result;
import com.example.fitnessapp.data.model.RegisteredUser;

import java.util.Objects;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<RegisterFormState> registerFormState = new MutableLiveData<>();
    private MutableLiveData<RegisterResult> registerResult = new MutableLiveData<>();
    private RegisterRepository registerRepository;

    public RegisterViewModel(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public RegisterViewModel() {

    }

    LiveData<RegisterFormState> getRegisterFormState() {
        return registerFormState;
    }

    LiveData<RegisterResult> getRegisterResult() {
        return registerResult;
    }

    public void register(String username, String password, String age, String height, String weight) {
        // can be launched in a separate asynchronous job
        Result<RegisteredUser> result = registerRepository.register(username, password, age, height, weight);

        if (result instanceof Result.Success) {
            RegisteredUser data = ((Result.Success<RegisteredUser>) result).getData();
            registerResult.setValue(new RegisterResult(new RegisteredUserView(data.getDisplayName())));
        } else {
            registerResult.setValue(new RegisterResult(R.string.register_failed));
        }
    }

    public void registerDataChanged(String username, String password, String age, String height, String weight) {
        if (!isUserNameValid(username)) {
            registerFormState.setValue(new RegisterFormState(R.string.invalid_username, null, null, null, null));
        } else if (!isPasswordValid(password)) {
            registerFormState.setValue(new RegisterFormState(null, R.string.invalid_password, null, null, null));
        } else if (!isAgeValid(age)) {
            registerFormState.setValue(new RegisterFormState(null, null, R.string.invalid_age, null, null));
        } else if (!isHeightValid(height)) {
            registerFormState.setValue(new RegisterFormState(null, null, null, R.string.invalid_height, null));
        } else if (!isWeightValid(weight)) {
            registerFormState.setValue(new RegisterFormState(null, null, null, null, R.string.invalid_weight));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
        }
    }

    // A placeholder username validation check
    public boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            return username.matches(emailRegex) && (username.length() > 5 && username.length() < 50);
        }
        return false;
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    private boolean isAgeValid(String age) {
        return !Objects.equals(age, "") && Integer.parseInt(age) > 10 && Integer.parseInt(age) < 100;
    }

    private boolean isHeightValid(String height) {
        return !Objects.equals(height, "") && Integer.parseInt(height) > 100 && Integer.parseInt(height) < 280;
    }

    private boolean isWeightValid(String weight) {
        return !Objects.equals(weight, "") && Integer.parseInt(weight) > 20 && Integer.parseInt(weight) < 250;
    }
}
