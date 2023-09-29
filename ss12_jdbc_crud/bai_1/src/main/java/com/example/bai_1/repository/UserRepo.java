package com.example.bai_1.repository;

import com.example.bai_1.model.User;
import com.example.bai_1.service.IUserService;
import com.example.bai_1.service.UserService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepo implements IUserRepo {
    private static final String SELECT = "select*from users;";
    private static final String INSERT = "insert into users(name,email,country) values(?,?,?)";
    private static final String UPDATE = "update users set name = ?,email= ?, country =? where id = ?;";
    private static final String DELETE = "delete from users where id = ?;";
    private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
    private final static String SELECT_BY_COUNTRY = "SELECT * FROM users WHERE country = ?;";


    @Override
    public List<User> selectAllUser() {
        Connection connection = DataRepo.getConnection();
        List<User> userList = new ArrayList<>();
        User user;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id,name,email,country);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    @Override
    public void insertUser(User user) {
        Connection connection = DataRepo.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User selectUser(int id) {
        Connection connection = DataRepo.getConnection();
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int idValue = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(idValue,name,email,country);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        if (selectUser(user.getId()) == null) {
            return false;
        }
        Connection connection = DataRepo.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    @Override
    public boolean deleteUser(int id) {
        Connection connection= DataRepo.getConnection();
        if(selectUser(id)==null){
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    @Override
    public List<User> findByCountry(String country) {
        Connection connection =DataRepo.getConnection();
        List<User> userList = new ArrayList<>();
        User user;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_COUNTRY);
            preparedStatement.setString(1,country);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String findCountry = resultSet.getString("country");
                user  = new User(id,name,email,country);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }
}
