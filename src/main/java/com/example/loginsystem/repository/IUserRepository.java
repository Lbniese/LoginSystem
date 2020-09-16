package com.example.loginsystem.repository;

import com.example.loginsystem.model.User;

import java.util.List;

public interface IUserRepository {

    boolean create(User u);

    User read(String email);

    List<User> readAll();


}
