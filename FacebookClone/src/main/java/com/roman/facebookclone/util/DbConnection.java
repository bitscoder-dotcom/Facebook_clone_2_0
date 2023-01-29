package com.roman.facebookclone.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DbConnection {
    INSTANCE;

    public DataSource getDatasource() {

//        HikariDataSource dataSource = new HikariDataSource();
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/facebookdb");
//            dataSource.setUsername("root");
//            dataSource.setPassword(String.valueOf("=-098"));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return dataSource;
        Connection con = null;
        try {
            Class.forName("com.sql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306" +
                    "/facebookdb", "root", "=-098");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (DataSource) con;
    }

    public boolean connectionTest(){
        try (Connection connection = getDatasource().getConnection()){
            return connection.isValid(0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(DbConnection.INSTANCE.connectionTest());
    }
}
