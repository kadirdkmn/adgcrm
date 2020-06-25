/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Firma;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DBConnection;

/**
 *
 * @author ABDULKADIR
 */
public class FirmaDAO extends DBConnection {

    private ArrayList<Firma> firmaList;

    public void create(Firma d) {
        String sql = "insert into firma(kod,aciklama) values(?, ?)";
        try {
            PreparedStatement ps = this.connect().prepareStatement(sql);
            ps.setString(1, d.getKod());
            ps.setString(2, d.getAciklama());
            ps.executeUpdate();
            this.connect().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int count() {
        int count = 0;
        String sql = "Select count(id) as firmasayisi from firma";
        firmaList = new ArrayList<>();
        try {
            PreparedStatement ps = this.connect().prepareStatement(sql);
            ResultSet veri = ps.executeQuery();
            while (veri.next()) {
                count = veri.getInt("firmasayisi");
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    } 
     
    public ArrayList<Firma> findAll(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        
        firmaList = new ArrayList<>();
        try {
             PreparedStatement ps = this.connect().prepareStatement("select * from firma order by id asc limit ? offset ?");
            ps.setInt(1, pageSize);
            ps.setInt(2, start);
            ResultSet veri = ps.executeQuery();
            while (veri.next()) {
                Firma firma = new Firma(veri.getInt(1), veri.getString(2), veri.getString(3));
                firmaList.add(firma);
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return firmaList;
    }

    public ArrayList<Firma> search(String firmakod) {
        firmaList = new ArrayList<>();

        firmakod = "%" + firmakod + "%";
        try {
            PreparedStatement ps = this.connect().prepareStatement("select * from firma where kod LIKE ?");
            ps.setString(1, firmakod);

            ResultSet veri = ps.executeQuery();

            while (veri.next()) {
                Firma firma = new Firma(veri.getInt(1), veri.getString(2), veri.getString(3));
                firmaList.add(firma);
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return firmaList;
    }

    public void update(Firma d) {
        try {
            PreparedStatement ps = this.connect().prepareStatement("UPDATE firma SET  kod=?, aciklama=? WHERE id=?");
            ps.setString(1, d.getKod());
            ps.setString(2, d.getAciklama());
            ps.setInt(3, d.getId());
            ps.executeUpdate();
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
    public void delete(Firma firma) {

        try {
            PreparedStatement ps = this.connect().prepareStatement("delete from firma where id=?");
            ps.setLong(1, firma.getId());
            ps.executeUpdate();
            connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Firma> getFirmaList() {
        if (firmaList == null) {
            firmaList = new ArrayList<Firma>();
        }
        return firmaList;
    }

    public void setFirmaList(ArrayList<Firma> firmaList) {
        this.firmaList = firmaList;
    }

}
