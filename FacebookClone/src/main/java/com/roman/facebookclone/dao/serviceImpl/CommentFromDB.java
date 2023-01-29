package com.roman.facebookclone.dao.serviceImpl;

import com.roman.facebookclone.model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentFromDB {

    public static List<Comment> getCommentsFromDB(int post_id) {
        List<Comment> commentList = new ArrayList<>();

        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/facebookdb?serverTimezone=UTC";
            String username = "root";
            String password3 = "=-098";

            Connection connection = DriverManager.getConnection(url, username, password3);

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT comment_content, id FROM comments WHERE post_id = ?;");

            statement.setInt(1, post_id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setComment_content(resultSet.getString(
                        "comment_content"));
                comment.setComment_id(resultSet.getInt("id"));
                comment.setPost_id(post_id);


                commentList.add(comment);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return commentList;
    }

}
