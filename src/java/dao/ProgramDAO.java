/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Program;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DBConnection;

/**
 *
 * @author GULAYOTMAN
 */
public class ProgramDAO extends DBConnection{
    private ArrayList<Program> programList;
public void create(Program p) {
        String sql = "insert into program(kod,aciklama) values(?, ?)";
        try {
            PreparedStatement ps = this.connect().prepareStatement(sql);
            ps.setString(1, p.getKod());
            ps.setString(2, p.getAciklama());
            ps.executeUpdate();
            this.connect().close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
     public int count() {
        int count = 0;
        String sql = "Select count(id) as programsayisi from program";
        programList = new ArrayList<>();
        try {
            PreparedStatement ps = this.connect().prepareStatement(sql);
            ResultSet veri = ps.executeQuery();
            while (veri.next()) {
                count = veri.getInt("programsayisi");
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }
     public void delete(Program program) {

        try {
            PreparedStatement ps = this.connect().prepareStatement("delete from program where id=?");
            ps.setLong(1, program.getId());
            ps.executeUpdate();
            connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public ArrayList<Program> findAll(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        programList = new ArrayList<>();
        try {
            PreparedStatement ps;
            ps = this.connect().prepareStatement("Select * from program order by id limit ? offset ?");
            ps.setInt(1, pageSize);
            ps.setInt(2, start);
            ResultSet veri = ps.executeQuery();
            while(veri.next()) {
                Program program = new Program(veri.getInt(1), veri.getString(2), veri.getString(3));
                programList.add(program);
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return programList;
    }
    
    public ArrayList<Program> search(String programkod){
        programList=new ArrayList<>();

        programkod="%"+programkod+"%";
        try{
            PreparedStatement ps=this.connect().prepareStatement("select * from program where kod LIKE ?");
            ps.setString(1, programkod);

            ResultSet veri=ps.executeQuery();
            
            while(veri.next()){
                Program program = new Program(veri.getInt(1), veri.getString(2), veri.getString(3));
                programList.add(program);
            }
            this.connect().close();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return programList;
    }
    
    public void update(Program p) {
        try{
            PreparedStatement ps = this.connect().prepareStatement("UPDATE program SET  kod=?, aciklama=? WHERE id=?");
            ps.setString(1,p.getKod());
            ps.setString(2,p.getAciklama());
            ps.setInt(3, p.getId());
            ps.executeUpdate();
            this.connect().close();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public ArrayList<Program> getProgramList() {
        if(programList == null){
        programList = new ArrayList<Program>();
        }
        return programList;        
    }

    public void setProgramList(ArrayList<Program> programList) {
        this.programList = programList;
    }
    
}
