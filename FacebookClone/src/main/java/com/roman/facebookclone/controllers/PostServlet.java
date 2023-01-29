package com.roman.facebookclone.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "PostServlet", value = "/homepage")
public class PostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcurl = "jdbc:mysql://localhost:3306/facebookdb?serverTimezone=UTC";
            String jdbcusername = "root";
            String jdbcpassword3 = "=-098";

            Connection connection = DriverManager.getConnection(jdbcurl, jdbcusername, jdbcpassword3);

            String post_content = request.getParameter("post_content");
            Integer userId = Integer.valueOf(request.getSession().getAttribute("user_id").toString());

            PreparedStatement statement = connection.prepareStatement("INSERT INTO posts (user_id, post_content) VALUES (?,?)");


            statement.setInt(1, userId);
            statement.setString(2, post_content);

            statement.executeUpdate();

            String foundUser = post_content;


            HttpSession requestSession = request.getSession();
            requestSession.setAttribute("loggedUser", foundUser);
            request.getRequestDispatcher("/homepage.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
