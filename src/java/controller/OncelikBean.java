/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OncelikDAO;
import entity.Oncelik;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author DAMLAC
 */
@Named
@SessionScoped
public class OncelikBean implements Serializable{
    
    private OncelikDAO oncelikDAO;
    private Oncelik oncelik;
    private ArrayList<Oncelik> oncelikList;
    private String aranan;
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }
        oncelikList = getOncelikDAO().findAll(page, pageSize);
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }

        oncelikList = getOncelikDAO().findAll(page, pageSize);
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
        this.pageCount = (int)Math.ceil(this.getOncelikDAO().count() / (double)pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String delete(Oncelik oncelik) {
        getOncelikDAO().delete(oncelik);
        return "oncelik";
    }

    public OncelikBean() {
        fullList();
    }
    
    public String create() {
        this.getOncelikDAO().create(oncelik);
        oncelik = new Oncelik();
        return "oncelik";
    }
    
    public String update() {
        getOncelikDAO().update(oncelik);
        oncelik = new Oncelik();
        return "oncelik";
    }
    
    public void search(){
        System.out.println("controller.OncelikBean.search()");
        oncelikList = new ArrayList<>();
        oncelikList = getOncelikDAO().search(aranan);
    }
    
    public void fullList(){
       oncelikList = getOncelikDAO().findAll(page, pageSize);
    }
    
    public void oncelikSec(Oncelik o) {
        oncelik = o;
    }
    
    public void formTemizle() {
        oncelik = new Oncelik();
    }

    public OncelikDAO getOncelikDAO() {
        if(this.oncelikDAO == null)
            this.oncelikDAO = new OncelikDAO();
        return oncelikDAO;
    }

    public void setOncelikDAO(OncelikDAO durumDAO) {
        this.oncelikDAO = oncelikDAO;
    }

    public Oncelik getOncelik() {
        if(this.oncelik == null)
            this.oncelik = new Oncelik();
        return oncelik;
    }

    public void setOncelik(Oncelik oncelik) {
        this.oncelik = oncelik;
    }

    public ArrayList<Oncelik> getOncelikList() {
         this.oncelikList = this.getOncelikDAO().findAll(page, pageSize);
        return oncelikList;
    }

    public void setOncelikList(ArrayList<Oncelik> oncelikList) {
        this.oncelikList = oncelikList;
    }

    public String getAranan() {
        return aranan;
    }

    public void setAranan(String aranan) {
        this.aranan = aranan;
    }
}
