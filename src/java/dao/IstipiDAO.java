/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Istipi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import util.DBConnection;

/**
 *
 * @author DAMLAC
 */
public class IstipiDAO extends DBConnection {

    private ArrayList<Istipi> isTipiList;

    public void create(Istipi ist) {
        String sql = "insert into istipi(kod,aciklama) values(?, ?)";
        try {
            PreparedStatement ps = this.connect().prepareStatement(sql);
            ps.setString(1, ist.getKod());
            ps.setString(2, ist.getAciklama());
            ps.executeUpdate();
            this.connect().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int count() {
        int count = 0;
        String sql = "Select count(id) as istipisayisi from istipi";
        isTipiList = new ArrayList<>();
        try {
            PreparedStatement ps = this.connect().prepareStatement(sql);
            ResultSet veri = ps.executeQuery();
            while (veri.next()) {
                count = veri.getInt("istipisayisi");
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }
     public void delete(Istipi istipi) {

        try {
            PreparedStatement ps = this.connect().prepareStatement("delete from istipi where id=?");
            ps.setLong(1, istipi.getId());
            ps.executeUpdate();
            connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Istipi> findAll(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        isTipiList = new ArrayList<>();
        try {
            PreparedStatement ps;
            ps = this.connect().prepareStatement("Select * from istipi order by id limit ? offset ?");
            ps.setInt(1, pageSize);
            ps.setInt(2, start);
            ResultSet veri = ps.executeQuery();
            while (veri.next()) {
                Istipi istipi = new Istipi();
                istipi.setId(veri.getInt(1));
                istipi.setKod(veri.getString(2));
                istipi.setAciklama(veri.getString(3));
                isTipiList.add(istipi);
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return isTipiList;
    }

    public ArrayList<Istipi> search(String istipikod) {
        isTipiList = new ArrayList<>();

        istipikod = "%" + istipikod + "%";
        try {
            PreparedStatement ps = this.connect().prepareStatement("select * from istipi where kod LIKE ?");
            ps.setString(1, istipikod);

            ResultSet veri = ps.executeQuery();

            while (veri.next()) {
                Istipi istipi = new Istipi(veri.getInt(1), veri.getString(2), veri.getString(3));
                isTipiList.add(istipi);
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return isTipiList;
    }

    public void update(Istipi ist) {
        try {
            PreparedStatement ps = this.connect().prepareStatement("UPDATE istipi SET  kod=?, aciklama=? WHERE id=?");
            ps.setString(1, ist.getKod());
            ps.setString(2, ist.getAciklama());
            ps.setInt(3, ist.getId());
            ps.executeUpdate();
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

   

    public ArrayList<Istipi> getIsTipiList() {
        if (isTipiList == null) {
            isTipiList = new ArrayList<Istipi>();
        }
        return isTipiList;
    }

    public void setIsTipiList(ArrayList<Istipi> isTipiList) {
        this.isTipiList = isTipiList;
    }

}
