package com.example.bai_1.repository;

import com.example.bai_1.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepo {
    List<User> selectAllUser();
    void insertUser(User user) throws SQLException;
    User selectUser(int id);
    boolean updateUser(User user);
    boolean deleteUser(int id);
    List<User> findByCountry(String country);
}
