package com.klf;

import java.sql.*;
import java.util.Scanner;

public class UserManagement {
    public static void main(String[] args) {
        Connection conn = DBConn.getConnection();
        System.out.println("User Management Module:\n");
        System.out.println("Create a user---------------1");
        System.out.println("Update user information-----2");
        System.out.println("Log in----------------------3");
        System.out.print("Enter your selection: ");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int selection;
        String username;
        String password;
        User user;
        try{
            selection = Integer.parseInt(str);
            switch (selection){
                case 1:
                    System.out.print("Enter username please:");
                    username = in.nextLine();
                    System.out.print("Enter password please:");
                    password = in.nextLine();
                    user = new User(username,password);
                    DBOperation.createUser(user);
                    break;
                case 2:
                    System.out.print("Enter username to update please:");
                    username = in.nextLine();
                    System.out.print("Enter password please:");
                    password = in.nextLine();
                    user = new User(username,password);
                    try {
                        DBOperation.updateUser(user);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.print("Enter username please:");
                    username = in.nextLine();
                    System.out.print("Enter password please:");
                    password = in.nextLine();
                    DBOperation.login(username,password);
                    break;
            }
        }catch (NumberFormatException e){
            System.out.println("You must enter 1,2,3");
        }
        DBConn.closeConnection();
    }
}
