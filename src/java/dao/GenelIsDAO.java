/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.GenelIs;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DBConnection;

/**
 *
 * @author ABDULKADIR
 */
public class GenelIsDAO extends DBConnection {

    private ArrayList<GenelIs> genelIsList;

    public void create(GenelIs g) {
        String sql = "insert into genelis(personelid, firmaid, istipiid, durumid, plandurumid, programid, oncelikid, isdetay, ekran, bolum) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, )";
        try {
            PreparedStatement ps = this.connect().prepareStatement(sql);
            /*ps.setInt(1, g.getPersonel());
            ps.setInt(2, g.getFirmaid());
            ps.setInt(3, g.getIstipiid());
            ps.setInt(4, g.getDurumid());
            ps.setInt(5, g.getPlandurumid());
            ps.setInt(6, g.getProgramid());
            ps.setInt(7, g.getOncelikid());
            ps.setString(8, g.getIsdetay());
            ps.setString(9, g.getEkran());
            ps.setString(10, g.getBolum());
*/
            ps.executeUpdate();
            this.connect().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int count() {
        int count = 0;
        String sql = "Select count(id) as genelissayısı from genelis";
        genelIsList = new ArrayList<>();
        try {
            PreparedStatement ps = this.connect().prepareStatement(sql);
            ResultSet veri = ps.executeQuery();
            while (veri.next()) {
                count = veri.getInt("genelissayısı");
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

    public void delete(GenelIs genelis) {

        try {
            PreparedStatement ps = this.connect().prepareStatement("delete from genelis where id=?");
            ps.setLong(1, genelis.getId());
            ps.executeUpdate();
            connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<GenelIs> findAll(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        genelIsList = new ArrayList<>();
        try {
            PreparedStatement ps;
            ps = this.connect().prepareStatement("Select * from genelis order by id limit ? offset ?");
            ps.setInt(1, pageSize);
            ps.setInt(2, start);
            ResultSet veri = ps.executeQuery();
            while (veri.next()) {
                /*GenelIs genelis = new GenelIs(veri.getInt(1), veri.getInt(2), veri.getInt(3), veri.getInt(4), veri.getInt(5), veri.getInt(6), veri.getInt(7), veri.getInt(8), veri.getString(9), veri.getString(10), veri.getString(11));
                genelIsList.add(genelis);*/
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return genelIsList;
    }

    public ArrayList<GenelIs> search(String geneliskod) {
        genelIsList = new ArrayList<>();

        geneliskod = "%" + geneliskod + "%";
        try {
            PreparedStatement ps = this.connect().prepareStatement("select * from genelis where kod LIKE ?");
            ps.setString(1, geneliskod);

            ResultSet veri = ps.executeQuery();

            while (veri.next()) {
                //GenelIs genelis = new GenelIs(veri.getInt(1), veri.getInt(2), veri.getInt(3), veri.getInt(4), veri.getInt(5), veri.getInt(6), veri.getInt(7), veri.getInt(8), veri.getString(9), veri.getString(10), veri.getString(11));
                //genelIsList.add(genelis);
            }
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return genelIsList;
    }

    public void update(GenelIs g) {
        try {
            PreparedStatement ps = this.connect().prepareStatement("UPDATE genelis SET  personelid = ?, firmaid = ?, istipiid = ?, durumid = ?, plandurumid = ?, programid = ?, oncelikid = ?, isdetay = ?, ekran = ?, bolum = ? WHERE id=?");
            /*ps.setInt(1, g.getPersonelid());
            ps.setInt(2, g.getFirmaid());
            ps.setInt(3, g.getIstipiid());
            ps.setInt(4, g.getDurumid());
            ps.setInt(5, g.getPlandurumid());
            ps.setInt(6, g.getProgramid());
            ps.setInt(7, g.getOncelikid());
            ps.setString(8, g.getIsdetay());
            ps.setString(9, g.getEkran());
            ps.setString(10, g.getBolum());
            ps.setInt(11, g.getId());*/
            ps.executeUpdate();
            this.connect().close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public ArrayList<GenelIs> getGenelIsList() {
        if (genelIsList == null) {
            genelIsList = new ArrayList<GenelIs>();
        }
        return genelIsList;
    }

    public void setGenelIsList(ArrayList<GenelIs> genelIsList) {
        this.genelIsList = genelIsList;
    }

}
