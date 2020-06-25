/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Durum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DBConnection;

/**
 *
 * @author ABDULKADIR
 */
public class DurumDAO extends DBConnection{
     private ArrayList<Durum> durumList;
    
    public void create(Durum d) {
        String sql = "insert into durum(kod,aciklama) values(?, ?)";
        try {
            PreparedStatement ps = this.connect().prepareStatement(sql);
            ps.setString(1, d.getKod());
            ps.setString(2, d.getAciklama());
            ps.executeUpdate();
            this.connect().close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
     public int count() {
         int count = 0 ;
        String sql = "Select count(id) as durumsayisi from durum";
        durumList=new ArrayList<>();
        try {
            PreparedStatement ps;
            ps = this.connect().prepareStatement(sql);
            ResultSet veri = ps.executeQuery();
            while(veri.next()) {
               count = veri.getInt("durumsayisi");
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }
    
    public ArrayList<Durum> findAll(int page, int pageSize) {
        int start = (page-1)* pageSize;
        
        durumList=new ArrayList<>();
        try {
            PreparedStatement ps = this.connect().prepareStatement("select * from durum order by id asc limit ? offset ?");
            ps.setInt(1, pageSize);
            ps.setInt(2, start);
            ResultSet veri = ps.executeQuery();
            while(veri.next()) {
                Durum durum = new Durum(veri.getInt(1), veri.getString(2), veri.getString(3));
                durumList.add(durum);
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return durumList;
    }
   
     public void delete(Durum durum) {

        try {
            PreparedStatement ps = this.connect().prepareStatement("delete from durum where id=?");
            ps.setLong(1, durum.getId());
            ps.executeUpdate();
            connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public ArrayList<Durum> search(String durumkod){
        durumList=new ArrayList<>();

        durumkod="%"+durumkod+"%";
        try{
            PreparedStatement ps=this.connect().prepareStatement("select * from durum where kod LIKE ?");
            ps.setString(1, durumkod);

            ResultSet veri=ps.executeQuery();
            
            while(veri.next()){
                Durum durum = new Durum(veri.getInt(1), veri.getString(2), veri.getString(3));
                durumList.add(durum);
            }
            this.connect().close();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return durumList;
    }
    
    public void update(Durum d) {
        try{
            PreparedStatement ps = this.connect().prepareStatement("UPDATE durum SET  kod=?, aciklama=? WHERE id=?");
            ps.setString(1,d.getKod());
            ps.setString(2,d.getAciklama());
            ps.setInt(3, d.getId());
            ps.executeUpdate();
            this.connect().close();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public ArrayList<Durum> getDurumList() {
        if (durumList == null) durumList = new ArrayList<Durum>();
        return durumList;
        
    }

    public void setDurumList(ArrayList<Durum> durumList) {
        this.durumList = durumList;
    }
    
}
