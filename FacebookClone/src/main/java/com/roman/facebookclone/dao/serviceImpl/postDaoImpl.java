package com.roman.facebookclone.dao.serviceImpl;

import com.roman.facebookclone.dao.PostDao;
import com.roman.facebookclone.model.Post;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class postDaoImpl implements PostDao {

    final static String POST_CONTENT = "INSERT INTO posts (post_content) VALUES (?)";

    @Override
    public Post postContent(@NonNull String post_content){

        Post postContent = new Post();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcurl = ("jdbc:mysql://localhost:3306/facebookdb?serverTimezone=UTC");
            String jdbcusr = "root";
            String jdbcpwd = "=-098";

            Connection connection = DriverManager.getConnection(jdbcurl, jdbcusr, jdbcpwd);

            PreparedStatement statement = connection.prepareStatement(POST_CONTENT);

            statement.setString(1, post_content);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return postContent;
    }
}
