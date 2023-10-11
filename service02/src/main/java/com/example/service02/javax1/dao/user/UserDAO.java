package com.example.service02.javax1.dao.user;

import com.example.service02.javax1.connection.DBConnection;
import com.example.service02.javax1.model.store.Store;
import com.example.service02.javax1.model.user.User;

import java.sql.*;
public class UserDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Change password
    public void ChangePassword(String pass,int id)
    {
        String sql = "update Users set userPassword =? where UserID =?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, pass);
            ps.setInt(2, id);

            ps.executeUpdate();

        }
        catch (Exception e)
        {

        }
    }

    // Set rating
    public void Rating(int storeID, double storeRating)
    {
        String sql="update Stores set storeRating =? where StoreID =?";
        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(2,storeID);
            ps.setDouble(1, storeRating);

            ps.executeUpdate();

        }
        catch(Exception e)
        {

        }
    }

    //Update email and password
    public User UserInfo(String userEmail, String userPassword)
    {
        String sql = "select * from Users where userEmail =? and userPassword =?";

        try {
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userEmail);
            ps.setString(2, userPassword);
            rs = ps.executeQuery();
            while(rs.next())
            {
                return new User (userEmail, userPassword);
            }
        }
        catch(Exception e)
        {

        }
        return null;
    }

    //Get user
    public User getUser(int userID) {
        String sqlString = "Select * from User where userID =?";
        try {

            Connection conn = new DBConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserID((User) rs.getRef(1));
                user.setUserName((User) rs.getRef(2));
                user.setUserEmail(String.valueOf((User) rs.getRef(3)));
                user.setUserPhoneNumber(String.valueOf((User) rs.getRef(4)));
                user.setUserUsername(String.valueOf((User) rs.getRef(5)));
                user.setUserPassword(String.valueOf((User) rs.getRef(6)));
                user.setUserAvatar(String.valueOf((User) rs.getRef(7)));
                user.setUserPoint(user.getUserPoint());
                user.setUserRole(user.getUserRole());
                user.setUserDateCreate(user.getUserDateCreate());
                return user;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    //Update User
    public void editUser(User user, int userID) {
        String sql = "UPDATE User SET userName=?, userPhoneNumber=?, userEmail=?, userAvatar=? WHERE userID=?";
        try {
            Connection conn= new DBConnection().getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(user.getUserName()));
            ps.setString(2, user.getUserPhoneNumber());
            ps.setString(3, user.getUserEmail());
            if (user.getUserAvatar() == null) ps.setString(4, getUser(userID).getUserAvatar());
            else ps.setString(4, user.getUserAvatar());
            ps.setInt(5, userID);
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }




}
