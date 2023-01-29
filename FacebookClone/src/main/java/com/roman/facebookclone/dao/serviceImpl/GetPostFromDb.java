package com.roman.facebookclone.dao.serviceImpl;

import com.roman.facebookclone.model.Post;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GetPostFromDb {

    public static List<Post> getPostsFromDB() {
        List<Post> postList = new ArrayList<>();

        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/facebookdb?serverTimezone=UTC";
            String username = "root";
            String password3 = "=-098";

            Connection connection = DriverManager.getConnection(url, username, password3);

            PreparedStatement statement = connection.prepareStatement("SELECT facebookdb.users.firstname,\n" +
                    "       facebookdb.users.surname,\n" +
                    "       post_id,\n" +
                    "       post_content,\n" +
                    "       post_time\n" +
                    "FROM users\n" +
                    "INNER JOIN posts ON users.user_id = posts.user_id ORDER BY post_time DESC;");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Post post = new Post();
                post.setPost_id(resultSet.getInt("post_id"));
                post.setPost_content(resultSet.getString("post_content"));
                post.setFirstname(resultSet.getString("firstname"));
                post.setSurname(resultSet.getString("surname"));
                post.setTime(LocalDate.parse(resultSet.getString("post_time").substring(0,10)));

                postList.add(post);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return postList;
    }
}