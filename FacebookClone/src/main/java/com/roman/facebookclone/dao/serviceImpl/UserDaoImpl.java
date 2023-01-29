package com.roman.facebookclone.dao.serviceImpl;

import com.roman.facebookclone.dao.UserDao;
import com.roman.facebookclone.dto.UserDto;
import com.roman.facebookclone.model.User;
import lombok.NonNull;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    final static String USER_INSERT_SQL = "INSERT INTO users (" +
            "firstname, surname, email, password, dob, gender) " + "VALUES (" +
            "?, ?, ?, ?, ?, ?)";

    final static String SELECT_USER_BY_USERNAME_AND_PASSWORD = "" +
            "SELECT * FROM users WHERE email = ? AND password = ?";


    public User saveUser(UserDto userDto){

        User savedUser = new User();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcurl = ("jdbc:mysql://localhost:3306/facebookdb");
            String jdbcusr = "root";
            String jdbcpwd = "=-098";

            Connection connection = DriverManager.getConnection(
                    jdbcurl, jdbcusr, jdbcpwd);

            PreparedStatement statement = connection.prepareStatement(
                    USER_INSERT_SQL);

            statement.setString(1, userDto.getFirstname());
            statement.setString(2, userDto.getSurname());
            statement.setString(3, userDto.getEmail());
            statement.setString(4, userDto.getPassword());
            statement.setString(5, userDto.getDob());
            statement.setString(6, userDto.getGender());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return savedUser;
    }


    public User findUserByEmailAndPassword(@NonNull String email,
                                           @NonNull String password){
        User foundUser = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcurl = ("jdbc:mysql://localhost:3306/facebookdb?serverTimezone=UTC");

            String jdbcusr = "root";
            String jdbcpwd = "=-098";

            Connection connection = DriverManager.getConnection(
                    jdbcurl,jdbcusr,jdbcpwd);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    SELECT_USER_BY_USERNAME_AND_PASSWORD);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String firstname = resultSet.getString("firstname");
                String surname = resultSet.getString("surname");
                String foundEmail = resultSet.getString("email");
                String foundPassword = resultSet.getString("password");
                String dob = resultSet.getString("dob");
                String gender = resultSet.getString("gender");
                Integer user_id = resultSet.getInt("user_id");

                foundUser = User.builder()
                        .firstname(firstname)
                        .surname(surname)
                        .email(foundEmail)
                        .password(foundPassword)
                        .dob(dob)
                        .gender(gender)
                        .id(user_id)
                        .build();
            }
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {

            System.err.println(e.getMessage());;
        }
        return foundUser;
    }

}
