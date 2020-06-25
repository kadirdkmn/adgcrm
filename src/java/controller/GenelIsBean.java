/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.GenelIsDAO;
import entity.GenelIs;
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
public class GenelIsBean implements Serializable{
    private GenelIsDAO genelIsDAO;
    private GenelIs genelIs;
    private ArrayList<GenelIs> genelIsList;
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
        genelIsList = getGenelIsDAO().findAll(page, pageSize);
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }

        genelIsList = getGenelIsDAO().findAll(page, pageSize);
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

    public String delete(GenelIs genelIs) {
        getGenelIsDAO().delete(genelIs);
        return "genelis";
    }


    public GenelIsBean() {
    }
    
    public String create() {
        this.getGenelIsDAO().create(genelIs);
        genelIs = new GenelIs();
        return "genelis";
    }
    
    public String update() {
        getGenelIsDAO().update(genelIs);
        genelIs = new GenelIs();
        return "genelis";
    }
    
    public void search(){
        System.out.println("controller.GenelIsBean.search()");
        genelIsList = new ArrayList<>();
        // istipiList = getGenelIsDAO().search(aranan);
    }
    
    public void fullList(){
       genelIsList = getGenelIsDAO().findAll(page, pageSize);
    }
    
    public void genelIsSec(GenelIs o) {
        genelIs = o;
    }
    
    public void formTemizle() {
        genelIs = new GenelIs();
    }

    public GenelIsDAO getGenelIsDAO() {
        if(this.genelIsDAO == null)
            this.genelIsDAO = new GenelIsDAO();
        return genelIsDAO;
    }

    public void setGenelIsDAO(GenelIsDAO durumDAO) {
        this.genelIsDAO = genelIsDAO;
    }

    public GenelIs getGenelIs() {
        if(this.genelIs == null)
            this.genelIs = new GenelIs();
        return genelIs;
    }

    public void setGenelIs(GenelIs genelIs) {
        this.genelIs = genelIs;
    }

    public ArrayList<GenelIs> getGenelIsList() {
        if(genelIsList == null){
        genelIsList = new ArrayList<>();
        }
        return genelIsList;
    }

    public void setGenelIsList(ArrayList<GenelIs> genelIsList) {
        this.genelIsList = genelIsList;
    }

    public String getAranan() {
        return aranan;
    }

    public void setAranan(String aranan) {
        this.aranan = aranan;
    }

    
    
}
