/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Personel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DBConnection;

/**
 *
 * @author ABDULKADIR
 */
public class PersonelDAO extends DBConnection {

    private ArrayList<Personel> personelList;
    public Personel getById(int id){
    Personel p = null;
        try {
            PreparedStatement ps = this.connect().prepareStatement("select * from personel where id = ?");
            ResultSet rs = ps.executeQuery();
            ps.setInt(1, id);
            rs.next();
            p = new Personel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        } catch (Exception e) {
            
        }
    return p;
    }
    public void create(Personel p) {
        String sql = "insert into personel(personelad,personelsoyad,personelcinsiyet,aciklama) values(?, ?, ?, ?)";
        try {
            PreparedStatement ps = this.connect().prepareStatement(sql);
            ps.setString(1, p.getPersonelad());
            ps.setString(2, p.getPersonelsoyad());
            ps.setString(3, p.getPersonelcinsiyet());
            ps.setString(4, p.getAciklama());
            ps.executeUpdate();
            this.connect().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int count() {
        int count = 0;
        String sql = "Select count(id) as perssayisi from personel";
        personelList = new ArrayList<>();
        try {
            PreparedStatement ps = this.connect().prepareStatement(sql);
            ResultSet veri = ps.executeQuery();
            while (veri.next()) {
                count = veri.getInt("perssayisi");
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    public ArrayList<Personel> findAll(int page, int pageSize) {
        int start = (page - 1) * pageSize;

        personelList = new ArrayList<>();
        try {
            PreparedStatement ps = this.connect().prepareStatement("select * from personel order by id asc limit ? offset ?");
            ps.setInt(1, pageSize);
            ps.setInt(2, start);
            ResultSet veri = ps.executeQuery();
            while (veri.next()) {
                Personel personel = new Personel(veri.getInt(1), veri.getString(2), veri.getString(3), veri.getString(4), veri.getString(5));
                personelList.add(personel);
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personelList;
    }

    public ArrayList<Personel> search(String personelad) {
        personelList = new ArrayList<>();

        personelad = "%" + personelad + "%";
        try {
            PreparedStatement ps = this.connect().prepareStatement("select * from personel where personelad LIKE ?");
            ps.setString(1, personelad);

            ResultSet veri = ps.executeQuery();

            while (veri.next()) {
                Personel personel = new Personel(veri.getInt(1), veri.getString(2), veri.getString(3), veri.getString(4), veri.getString(5));
                personelList.add(personel);
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personelList;
    }

    public void update(Personel p) {
        try {
            PreparedStatement ps = this.connect().prepareStatement("UPDATE personel SET  personelad=?, personelsoyad=?, personelcinsiyet=?, aciklama=? WHERE id=?");
            ps.setString(1, p.getPersonelad());
            ps.setString(2, p.getPersonelsoyad());
            ps.setString(3, p.getPersonelcinsiyet());
            ps.setString(4, p.getAciklama());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public void delete(Personel personel) {

        try {
            PreparedStatement ps = this.connect().prepareStatement("delete from personel where id=?");
            ps.setLong(1, personel.getId());
            ps.executeUpdate();
            connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Personel> getPersonelList() {
        if (personelList == null) {
            personelList = new ArrayList<Personel>();
        }
        return personelList;
    }

    public void setPersonelList(ArrayList<Personel> personelList) {
        this.personelList = personelList;
    }
}
