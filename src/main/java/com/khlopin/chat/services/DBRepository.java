package com.khlopin.chat.services;

import com.khlopin.chat.entity.Message;
import com.khlopin.chat.entity.User;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBRepository {

    private static final String URL_FOR_DB = "jdbc:mysql://localhost:3306/ChatDB";
    private static final String LOGIN_FOR_DB = "root";
    private static final String PASSWORD_FOR_DB = "123456";

    //TODO добавить код, который будет отлавливать Execption и делать rollBack, так же отключить autoComit

    @SneakyThrows
    public static List<Message> getMessageList() {
        List<Message> messageList = new ArrayList<>();
        String sql = "SELECT * from ChatDB.message";
        try (Connection connect = connect();
             PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                messageList.add(Message.builder()
                                .id(resultSet.getLong(1))
                                .text(resultSet.getString(2))
                                .ownerOfMessage(findUserFromDbByID(resultSet.getLong(4)))
                        .build());
            }
        }
        return messageList;
    }

    @SneakyThrows
    public static void addMessageInDB(Message message) {
        String sqlRequest = "INSERT INTO ChatDB.message (text, t_user_id) VALUES (?,?)";
        try (Connection connect = connect();
             PreparedStatement preparedStatement = connect.prepareStatement(sqlRequest)) {
            preparedStatement.setString(1, message.getText());
            preparedStatement.setLong(2,message.getOwnerOfMessage().getId());
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public static void addUserInDB(User user) {
        String sqlRequest = "INSERT INTO ChatDB.t_user (login, password, chat_id) VALUES (?,?,?)";
        try (Connection connect = connect();
             PreparedStatement preparedStatement = connect.prepareStatement(sqlRequest)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, 1);
            preparedStatement.executeUpdate();
        }

    }



    public static User findUserFromDbByID(long id) {
        try (Connection connect = connect();
             PreparedStatement statement = connect.prepareStatement("select * from t_user where id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return User.builder()
                    .id(resultSet.getLong("id"))
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password")).build();
        } catch (SQLException e) {
            return null;
        }
    }


    public static User findUserFromDbByLogin(String login) {
        try (Connection connect = connect();
             PreparedStatement statement = connect.prepareStatement("select * from t_user where login = ?")) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return User.builder()
                    .id(resultSet.getLong("id"))
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password")).build();
        } catch (SQLException e) {
            return null;
        }
    }

    @SneakyThrows
    private static Connection connect() {
        return DriverManager.getConnection(URL_FOR_DB, LOGIN_FOR_DB, PASSWORD_FOR_DB);
    }

}
