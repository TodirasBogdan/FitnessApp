package com.example.fitnessapp.ui.register;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitnessapp.data.RegisterDataSource;
import com.example.fitnessapp.data.RegisterRepository;


public class RegisterViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(RegisterRepository.getInstance(new RegisterDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
