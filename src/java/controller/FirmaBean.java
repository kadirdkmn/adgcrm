/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FirmaDAO;
import entity.Firma;
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
public class FirmaBean implements Serializable {

    private FirmaDAO firmaDAO;
    private Firma firma;
    private ArrayList<Firma> firmaList;
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
        firmaList = getFirmaDAO().findAll(page, pageSize);
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }

        firmaList = getFirmaDAO().findAll(page, pageSize);
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
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String delete(Firma firma) {
        getFirmaDAO().delete(firma);
        return "firma";
    }

    public FirmaBean() {
    }

    public String create() {
        this.getFirmaDAO().create(firma);
        firma = new Firma();
        return "firma";
    }

    public String update() {
        getFirmaDAO().update(firma);
        firma = new Firma();
        return "firma";
    }

    public void search() {
        System.out.println("controller.FirmaBean.search()");
        firmaList = new ArrayList<>();
        // firmaList = getFirmaDAO().search(aranan);
    }

    public void fullList() {
        firmaList = getFirmaDAO().findAll(page, pageSize);
    }

    public void firmaSec(Firma d) {
        firma = d;
    }

    public void formTemizle() {
        firma = new Firma();
    }

    public FirmaDAO getFirmaDAO() {
        if (this.firmaDAO == null) {
            this.firmaDAO = new FirmaDAO();
        }
        return firmaDAO;
    }

    public void setFirmaDAO(FirmaDAO firmaDAO) {
        this.firmaDAO = firmaDAO;
    }

    public Firma getFirma() {
        if (this.firma == null) {
            this.firma = new Firma();
        }
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public ArrayList<Firma> getFirmaList() {
         this.firmaList=this.getFirmaDAO().findAll(page, pageSize);
        return firmaList;
    
    }

    public void setFirmaList(ArrayList<Firma> firmaList) {
        this.firmaList = firmaList;
    }

    public String getAranan() {
        return aranan;
    }

    public void setAranan(String aranan) {
        this.aranan = aranan;
    }
}
