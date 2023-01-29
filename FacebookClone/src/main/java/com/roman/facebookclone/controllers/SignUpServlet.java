package com.roman.facebookclone.controllers;

import com.roman.facebookclone.dao.UserDao;
import com.roman.facebookclone.dao.serviceImpl.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "SignUpServlet", value = "/sign-up")
public class SignUpServlet extends HttpServlet {

    private static UserDao userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/facebookdb?serverTimezone=UTC";
            String username = "root";
            String password2 = "=-098";

            String firstname = request.getParameter("firstname");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");


            Connection con = DriverManager.getConnection(url, username, password2);

            PreparedStatement statement = con.prepareStatement("INSERT INTO " +
                    "users(firstname, surname, email, password, dob, gender) " +
                    "VALUES (?, ?, ?, ?, ?, ?)");


            statement.setString(1, firstname);
            statement.setString(2, surname);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, dob);
            statement.setString(6, gender);

            statement.executeUpdate();

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("/homepage.jsp").forward(request, response);
    }
}
