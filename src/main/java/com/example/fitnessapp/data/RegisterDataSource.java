package com.example.fitnessapp.data;

import com.example.fitnessapp.data.model.RegisteredUser;

import java.io.IOException;

public class RegisterDataSource {

    public Result<RegisteredUser> register(String username, String password, String age, String height, String weight) {

        try {
            // TODO: handle RegisteredUser authentication
            RegisteredUser fakeUser =
                    new RegisteredUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error registering", e));
        }
    }

    public void registerOut() {
        // TODO: revoke authentication
    }
}
