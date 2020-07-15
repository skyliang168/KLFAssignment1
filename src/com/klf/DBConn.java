package com.klf;

import java.sql.*;

public class DBConn {
    private static Connection conn;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver fails");
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_management",
                                "root", "lww1234");
        } catch (SQLException e) {
            System.out.println("Connection fails");
            e.printStackTrace();
        }
        return conn;
    }


    public static void closeConnection() {
        if(ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
