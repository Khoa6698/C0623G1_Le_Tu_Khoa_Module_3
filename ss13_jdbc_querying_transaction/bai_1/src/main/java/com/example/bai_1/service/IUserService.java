package com.example.bai_1.service;

import com.example.bai_1.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    List<User> selectAllUser();
    void insertUser(User user) throws SQLException;
    User selectUser(int id);
    boolean updateUser(User user);
    boolean deleteUser(int id);
    List<User> findByCountry(String country);
    User getUserById (int id);

    void insertUserStore (User user);

    List<User> displayProcedure();

    void editProcedure(int id, User user);

    void deleteProcedure(int id);

    void addTransaction(User user, List<Integer> permissions);

    void updateTransaction();
}
