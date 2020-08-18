package commandUriTest.model.dao;

import commandUriTest.model.dto.User;
import dbConnection.DBConnectionMgr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
    DBConnectionMgr dbConnectionMgr = new DBConnectionMgr();
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public int insert(User user) {
        int result = 0;
        String query = "insert into users values(default , ?, ?, ?, ?, ?);";

        try {
            connection = dbConnectionMgr.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhone());

            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConnectionMgr.freeConnection(connection, preparedStatement);
        }

        return result;
    }

    public ArrayList<User> select() {
        ArrayList<User> users = new ArrayList<>();
        String query = "select * from users order by level desc, id;";

        try {
            connection = dbConnectionMgr.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbConnectionMgr.freeConnection(connection, preparedStatement, resultSet);
        }

        return users;
    }

}
