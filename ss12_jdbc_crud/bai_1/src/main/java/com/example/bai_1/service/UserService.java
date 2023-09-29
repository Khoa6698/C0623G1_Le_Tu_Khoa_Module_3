package com.example.bai_1.service;

import com.example.bai_1.model.User;
import com.example.bai_1.repository.IUserRepo;
import com.example.bai_1.repository.UserRepo;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService{
    private static final IUserRepo userRepo =  new UserRepo();
    @Override
    public List<User> selectAllUser() {
        return userRepo.selectAllUser();
    }

    @Override
    public void insertUser(User user) throws SQLException {
        userRepo.insertUser(user);
    }

    @Override
    public User selectUser(int id) {
        return userRepo.selectUser(id);
    }

    @Override
    public boolean updateUser(User user){
        return userRepo.updateUser(user);
    }

    @Override
    public boolean deleteUser(int id) {
        return userRepo.deleteUser(id);
    }

    @Override
    public List<User> findByCountry(String country) {
        return userRepo.findByCountry(country);
    }
}
