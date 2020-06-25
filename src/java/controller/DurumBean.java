/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DurumDAO;
import entity.Durum;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ABDULKADIR
 */
@Named
@SessionScoped
public class DurumBean implements Serializable{
    private DurumDAO durumDAO;
    private Durum durum;
    private ArrayList<Durum> durumList;
    private String aranan;
     private int page =1;
    private int pageSize = 10;
    private int pageCount ;

    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }
        durumList = getDurumDAO().findAll(page, pageSize);
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }

        durumList = getDurumDAO().findAll(page, pageSize);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getDurumDAO().count()/(double)pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    

    public DurumBean() {
        fullList();
    }
    
    public String create() {
        this.getDurumDAO().create(durum);
        durum = new Durum();
        return "durum";
    }
    
    public String update() {
        getDurumDAO().update(durum);
        durum = new Durum();
        return "durum";
    }
    
    public void search(){
        System.out.println("controller.DurumBean.search()");
        durumList = new ArrayList<>();
        durumList = getDurumDAO().search(aranan);
    }
    
    public void fullList(){
        durumList = getDurumDAO().findAll(page,pageSize);
    }
    public String delete(Durum durum) {
        getDurumDAO().delete(durum);
        return "personel";
    }
    public void durumSec(Durum d) {
        durum = d;
    }
    
    public void formTemizle() {
        durum = new Durum();
    }

    public DurumDAO getDurumDAO() {
        if(this.durumDAO == null)
            this.durumDAO = new DurumDAO();
        return durumDAO;
    }

    public void setDurumDAO(DurumDAO durumDAO) {
        this.durumDAO = durumDAO;
    }

    public Durum getDurum() {
        if(this.durum == null)
            this.durum = new Durum();
        return durum;
    }

    public void setDurum(Durum durum) {
        this.durum = durum;
    }

    public ArrayList<Durum> getDurumList() {
         this.durumList=this.getDurumDAO().findAll(page, pageSize);
        return durumList;
    
    }

    public void setDurumList(ArrayList<Durum> durumList) {
        this.durumList = durumList;
    }

    public String getAranan() {
        return aranan;
    }

    public void setAranan(String aranan) {
        this.aranan = aranan;
    }
}
