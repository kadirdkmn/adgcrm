/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.IstipiDAO;
import entity.Istipi;
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
public class IsTipiBean implements Serializable {

    private IstipiDAO istipiDAO;
    private Istipi istipi;
    private ArrayList<Istipi> istipiList;
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
        istipiList = getIstipiDAO().findAll(page, pageSize);
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }

        istipiList = getIstipiDAO().findAll(page, pageSize);
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

    public String delete(Istipi isTipi) {
        getIstipiDAO().delete(isTipi);
        return "istipi";
    }

    public IsTipiBean() {
    }

    public String create() {
        this.getIstipiDAO().create(istipi);
        istipi = new Istipi();
        return "istipi";
    }

    public String update() {
        getIstipiDAO().update(istipi);
        istipi = new Istipi();
        return "istipi";
    }

    public void search() {
        System.out.println("controller.IsTipiBean.search()");
        istipiList = new ArrayList<>();
        istipiList = getIstipiDAO().search(aranan);
    }

    public void fullList() {
        istipiList = getIstipiDAO().findAll(page, pageSize);
    }

    public void istipiSec(Istipi ist) {
        istipi = ist;
    }

    public void formTemizle() {
        istipi = new Istipi();
    }

    public IstipiDAO getIstipiDAO() {
        if (this.istipiDAO == null) {
            this.istipiDAO = new IstipiDAO();
        }
        return istipiDAO;
    }

    public void setIstipiDAO(IstipiDAO istipiDAO) {
        this.istipiDAO = istipiDAO;
    }

    public Istipi getIstipi() {
        if (this.istipi == null) {
            this.istipi = new Istipi();
        }
        return istipi;
    }

    public void setIstipi(Istipi istipi) {
        this.istipi = istipi;
    }

    public ArrayList<Istipi> getIstipiList() {
       this.istipiList = this.getIstipiDAO().findAll(page, pageSize);
        return istipiList;
    }

    public void setIstipiList(ArrayList<Istipi> istipiList) {
        this.istipiList = istipiList;
    }

    public String getAranan() {
        return aranan;
    }

    public void setAranan(String aranan) {
        this.aranan = aranan;
    }
}
