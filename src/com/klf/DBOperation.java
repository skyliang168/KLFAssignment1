package com.klf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOperation {
    public static void createUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConn.getConnection();
            String sql = "INSERT INTO user (username,password) VALUES(?,?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(ps != null) {
                try {
                    ps.close();
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

    public static void updateUser(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConn.getConnection();
            String sql = "UPDATE user SET password=? WHERE username = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, user.getPassword());
            ps.setString(2, user.getUsername());

            ps.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                conn.close();
            }
        }
    }

    public static void login(String username,String password){
        Connection conn = null;
        PreparedStatement ps = null;
//		Statement stmt;
        ResultSet rs = null;

        try {
            conn = DBConn.getConnection();
            String sql = "SELECT * FROM user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            Boolean isExist = false;
            while(rs.next()) {
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                if((username.equals(username1)) && (password.equals(password1))){
                    System.out.println("login succeeds!");
                    isExist = true;
                    return;
                }
            }
            if(!isExist) {
                System.out.println("The username and password do not exist!");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null) {
                try {
                    ps.close();
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
}
