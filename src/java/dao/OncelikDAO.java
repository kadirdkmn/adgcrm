/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Oncelik;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DBConnection;

/**
 *
 * @author DAMLAC
 */
public class OncelikDAO extends DBConnection {

    private ArrayList<Oncelik> oncelikList;

    public void create(Oncelik o) {
        String sql = "insert into oncelik(kod,aciklama) values(?, ?)";
        try {
            PreparedStatement ps = this.connect().prepareStatement(sql);
            ps.setString(1, o.getKod());
            ps.setString(2, o.getAciklama());
            ps.executeUpdate();
            this.connect().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int count() {
        int count = 0;
        String sql = "Select count(id) as onceliksayisi from oncelik";
        oncelikList = new ArrayList<>();
        try {
            PreparedStatement ps = this.connect().prepareStatement(sql);
            ResultSet veri = ps.executeQuery();
            while (veri.next()) {
                count = veri.getInt("onceliksayisi");
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    public void delete(Oncelik oncelik) {

        try {
            PreparedStatement ps = this.connect().prepareStatement("delete from oncelik where id=?");
            ps.setLong(1, oncelik.getId());
            ps.executeUpdate();
            connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Oncelik> findAll(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        oncelikList = new ArrayList<>();
        try {
            PreparedStatement ps;
            ps = this.connect().prepareStatement("Select * from oncelik order by id limit ? offset ?");
            ps.setInt(1, pageSize);
            ps.setInt(2, start);
            ResultSet veri = ps.executeQuery();
            while (veri.next()) {
                Oncelik oncelik = new Oncelik(veri.getInt(1), veri.getString(2), veri.getString(3));
                oncelikList.add(oncelik);
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return oncelikList;
    }

    public ArrayList<Oncelik> search(String oncelikkod) {
        oncelikList = new ArrayList<>();

        oncelikkod = "%" + oncelikkod + "%";
        try {
            PreparedStatement ps = this.connect().prepareStatement("select * from oncelik where kod LIKE ?");
            ps.setString(1, oncelikkod);

            ResultSet veri = ps.executeQuery();

            while (veri.next()) {
                Oncelik oncelik = new Oncelik(veri.getInt(1), veri.getString(2), veri.getString(3));
                oncelikList.add(oncelik);
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return oncelikList;
    }

    public void update(Oncelik o) {
        try {
            PreparedStatement ps = this.connect().prepareStatement("UPDATE oncelik SET  kod=?, aciklama=? WHERE id=?");
            ps.setString(1, o.getKod());
            ps.setString(2, o.getAciklama());
            ps.setInt(3, o.getId());
            ps.executeUpdate();
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public ArrayList<Oncelik> getOncelikList() {
        if (oncelikList == null) {
            oncelikList = new ArrayList<Oncelik>();
        }
        return oncelikList;
    }

    public void setOncelikList(ArrayList<Oncelik> oncelikList) {
        this.oncelikList = oncelikList;
    }

}
