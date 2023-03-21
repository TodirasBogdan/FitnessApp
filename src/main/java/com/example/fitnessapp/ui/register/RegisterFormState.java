package com.example.fitnessapp.ui.register;

import androidx.annotation.Nullable;

public class RegisterFormState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    @Nullable
    private Integer ageError;
    @Nullable
    private Integer heightError;
    @Nullable
    private Integer weightError;
    private boolean isDataValid;

    RegisterFormState(@Nullable Integer usernameError, @Nullable Integer passwordError, @Nullable Integer ageError, @Nullable Integer heightError, @Nullable Integer weightError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.ageError = ageError;
        this.heightError = heightError;
        this.weightError = weightError;
        this.isDataValid = false;
    }

    RegisterFormState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.ageError = null;
        this.heightError = null;
        this.weightError = null;
        this.isDataValid = isDataValid;
    }

    public void setUsernameError(@Nullable Integer usernameError) {
        this.usernameError = usernameError;
    }

    public void setPasswordError(@Nullable Integer passwordError) {
        this.passwordError = passwordError;
    }

    public void setAgeError(@Nullable Integer ageError) {
        this.ageError = ageError;
    }

    public void setHeightError(@Nullable Integer heightError) {
        this.heightError = heightError;
    }

    public void setWeightError(@Nullable Integer weightError) {
        this.weightError = weightError;
    }

    public void setDataValid(boolean dataValid) {
        isDataValid = dataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    Integer getAgeError() { return ageError;}

    @Nullable
    Integer getHeightError() {
        return heightError;
    }

    @Nullable
    Integer getWeightError() { return weightError;}

    boolean isDataValid() {
        return isDataValid;
    }
}
