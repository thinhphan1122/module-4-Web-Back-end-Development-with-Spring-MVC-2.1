package com.codegym.service.impl;

import com.codegym.model.User;
import com.codegym.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService implements IUserService {
    @Autowired
    private IUserService iUserService;
    @Override
    public List<User> findAll() {
        return iUserService.findAll();
    }

    @Override
    public void save(User user) {
        iUserService.save(user);
    }

    @Override
    public User findById(int id) {
        return iUserService.findById(id);
    }

    @Override
    public void remove(int id) {
        iUserService.remove(id);
    }
}
