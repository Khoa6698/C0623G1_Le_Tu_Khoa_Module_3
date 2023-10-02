package com.example.bai_1.repository;

import com.example.bai_1.model.User;
import com.example.bai_1.service.IUserService;
import com.example.bai_1.service.UserService;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserRepo implements IUserRepo {
    private static final String SELECT = "select*from users;";
    private static final String INSERT = "insert into users(name,email,country) values(?,?,?)";
    private static final String UPDATE = "update users set name = ?,email= ?, country =? where id = ?;";
    private static final String DELETE = "delete from users where id = ?;";
    private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
    private final static String SELECT_BY_COUNTRY = "SELECT * FROM users WHERE country = ?;";
    private final static String SQLPIVOT = "INSERT INTO user_permision(user_id,permision_id) " + "values(?,?)";
    private final static String INSERT_USER = "{CALL insert_user(?,?,?)}";
    private final static String SELECT_SP = "{CALL display_list_user()}";
    private final static String UPDATE_SP = "{CALL edit_user(?,?,?,?)}";
    private final static String DELETE_SP = "{CALL delete_user(?)}";
    private final static String GET_BY_ID_USER = "{CALL get_user_by_id(?)}";
    private final static String UPDATE_TRANSACTION = "UPDATE Employee SET salary=? WHERE `name`=?";
    private final static String INSERT_TRANSACTION = "INSERT INTO Employee (`name`, salary, created_date)\n" +
            "VALUES (?,?,?)";
    private final static String DROP = "DROP TABLE IF EXISTS Employee";
    private final static String CREATE = "CREATE TABLE Employee";



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

    @Override
    public User getUserById(int id) {
        Connection connection = DataRepo.getConnection();
        User user = null;
        try {
            CallableStatement callableStatement = connection.prepareCall(GET_BY_ID_USER);
            callableStatement.setInt(1,id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id,name,email,country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void insertUserStore(User user) {
        Connection connection = DataRepo.getConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall(INSERT_USER);
            callableStatement.setString(1,user.getName());
            callableStatement.setString(2,user.getEmail());
            callableStatement.setString(3,user.getCountry());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> displayProcedure() {
        Connection connection = DataRepo.getConnection();
        List<User> userList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall(SELECT_SP);
            ResultSet resultSet = callableStatement.executeQuery();
            User user;
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
    public void editProcedure(int id, User user) {
        Connection connection = DataRepo.getConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall(UPDATE_SP);
            callableStatement.setString(1,user.getName());
            callableStatement.setString(2,user.getEmail());
            callableStatement.setString(3,user.getCountry());
            callableStatement.setInt(4,user.getId());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProcedure(int id) {
        Connection connection = DataRepo.getConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall(DELETE_SP);
            callableStatement.setInt(1,id);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTransaction(User user, List<Integer> permissions) {
        Connection connection =DataRepo.getConnection();
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementAssignment = null;
        ResultSet resultSet = null;
        try {
            connection = DataRepo.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            int rowAffected = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            int idUser = 0;
            if(resultSet.next()){
                idUser = resultSet.getInt(1);
            }
            if (rowAffected ==1){
                preparedStatementAssignment = connection.prepareStatement(SQLPIVOT);
                for (int premissionId : permissions){
                    preparedStatementAssignment.setInt(1,idUser);
                    preparedStatementAssignment.setInt(2,premissionId);
                    preparedStatementAssignment.executeUpdate();
                }
                connection.commit();
            }else {
                connection.rollback();
            }
        } catch (SQLException e) {
            try {
                if (connection!=null){
                    connection.rollback();
                }
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }finally {
            try {
                if(resultSet!=null) resultSet.close();
                if(preparedStatement!=null) preparedStatement.close();
                if(preparedStatementAssignment!=null) preparedStatementAssignment.close();
                if(connection!=null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateTransaction() {
        Connection connection = DataRepo.getConnection();
        try {
            Statement statement = connection.createStatement();
            PreparedStatement insertPr = connection.prepareStatement(INSERT_TRANSACTION);
            PreparedStatement updatePr = connection.prepareStatement(UPDATE_TRANSACTION);
            statement.execute(DROP);
            statement.execute(CREATE);
            connection.setAutoCommit(false);
            insertPr.setString(1,"tung");
            insertPr.setBigDecimal(2, new BigDecimal(10));
            insertPr.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            insertPr.execute();

            updatePr.setBigDecimal(1, new BigDecimal(999.9));
            updatePr.setString(2,"toan");
            updatePr.execute();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }
    }
}
