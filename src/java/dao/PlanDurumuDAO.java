/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entity.PlanDurumu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DBConnection;

/**
 *
 * @author GULAYOTMAN
 */
public class PlanDurumuDAO extends DBConnection{
    private ArrayList<PlanDurumu> planDurumuList;
    public void create(PlanDurumu pd) {
        String sql = "insert into plandurumu(kod,aciklama) values(?, ?)";
        try {
            PreparedStatement ps = this.connect().prepareStatement(sql);
            ps.setString(1, pd.getKod());
            ps.setString(2, pd.getAciklama());
            ps.executeUpdate();
            this.connect().close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
     public int count() {
        int count = 0;
        String sql = "Select count(id) as plandurumusayisi from plandurumu";
        planDurumuList = new ArrayList<>();
        try {
            PreparedStatement ps = this.connect().prepareStatement(sql);
            ResultSet veri = ps.executeQuery();
            while (veri.next()) {
                count = veri.getInt("plandurumusayisi");
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }
     public void delete(PlanDurumu plandurumu) {

        try {
            PreparedStatement ps = this.connect().prepareStatement("delete from plandurumu where id=?");
            ps.setLong(1, plandurumu.getId());
            ps.executeUpdate();
            connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public ArrayList<PlanDurumu> findAll(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        planDurumuList = new ArrayList<>();
        try {
            PreparedStatement ps;
            ps = this.connect().prepareStatement("Select * from plandurumu order by id limit ? offset ?");
            ps.setInt(1, pageSize);
            ps.setInt(2, start);
            ResultSet veri = ps.executeQuery();
            while(veri.next()) {
                PlanDurumu planDurumu = new PlanDurumu(veri.getInt(1), veri.getString(2), veri.getString(3));
                planDurumuList.add(planDurumu);
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return planDurumuList;
    }
    
    public ArrayList<PlanDurumu> search(String planDurumukod){
        planDurumuList=new ArrayList<>();

        planDurumukod="%"+planDurumukod+"%";
        try{
            PreparedStatement ps=this.connect().prepareStatement("select * from plandurumu where kod LIKE ?");
            ps.setString(1, planDurumukod);

            ResultSet veri=ps.executeQuery();
            
            while(veri.next()){
                PlanDurumu planDurumu = new PlanDurumu(veri.getInt(1), veri.getString(2), veri.getString(3));
                planDurumuList.add(planDurumu);
            }
            this.connect().close();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return planDurumuList;
    }
    
    public void update(PlanDurumu pd) {
        try{
            PreparedStatement ps = this.connect().prepareStatement("UPDATE plandurumu SET  kod=?, aciklama=? WHERE id=?");
            ps.setString(1,pd.getKod());
            ps.setString(2,pd.getAciklama());
            ps.setInt(3, pd.getId());
            ps.executeUpdate();
            this.connect().close();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public ArrayList<PlanDurumu> getPlanDurumuList() {
        if(planDurumuList == null){
        planDurumuList = new ArrayList<PlanDurumu>();
        }
        return planDurumuList;        
    }

    public void setPlanDurumuList(ArrayList<PlanDurumu> planDurumuList) {
        this.planDurumuList = planDurumuList;
    }
}
