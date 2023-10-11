package com.example.service02.javax1.dao.store;

import com.example.service02.javax1.connection.DBConnection;
import com.example.service02.javax1.model.store.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StoreDAO {

    public void editStore(Store store, int storeID) {
        String sql = "UPDATE Store SET storeName =?, storeAvatar =?, storeEmailContact =? WHERE storeID =?";
        try {
            Connection conn = new DBConnection().getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(store.getStoreName()));
            ps.setString(2, store.getStoreAvatar());
            ps.setString(2, store.getStoreEmailContact());
            ps.setInt(3, storeID);
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


}
