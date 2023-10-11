package com.example.service02.javax1.dao.admin;

import com.example.service02.javax1.connection.DBConnection;
import com.example.service02.javax1.model.admin.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class AdminDAO {

    //Get user
    public Admin getAdmin(int adminID) {
        String sqlString = "Select * from Admin where adminID =?";
        try {

            Connection conn = new DBConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setInt(1,adminID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminID(((Admin) rs.getRef(1)).getAdminID());
                admin.setAdminName(String.valueOf((Admin) rs.getRef(2)));
                admin.setAdminPassword(String.valueOf((Admin) rs.getRef(3)));
                admin.setAdminDateCreate(LocalDateTime.parse(String.valueOf((Admin) rs.getRef(4))));

                return admin;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}
