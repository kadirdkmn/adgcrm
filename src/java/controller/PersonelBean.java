/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PersonelDAO;
import entity.Personel;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ABDULKADİR
 */
@SessionScoped
@Named
public class PersonelBean implements Serializable {

    private PersonelDAO personelDAO;
    private Personel personel;
    private ArrayList<Personel> personelList;
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
        personelList = getPersonelDAO().findAll(page, pageSize);
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }

        personelList = getPersonelDAO().findAll(page, pageSize);
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
        this.pageCount = (int) Math.ceil(this.getPersonelDAO().count() / (double) pageSize);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public PersonelBean() {
        fullList();
    }

    public String create() {
        this.getPersonelDAO().create(personel);
        personel = new Personel();
        return "personel";
    }

    public String update() {
        getPersonelDAO().update(personel);
        personel = new Personel();
        return "personel";
    }

    public void search() {
        System.out.println("controller.PersonelBean.search()");
        personelList = new ArrayList<>();
        personelList = getPersonelDAO().search(aranan);
    }
    public Personel getById(int id){
    return this.getPersonelDAO().getById(id);
    }
    public void fullList() {
        personelList = getPersonelDAO().findAll(page, pageSize);
    }

    public void personelSec(Personel p) {
        personel = p;
    }

    public String delete(Personel personel) {
        getPersonelDAO().delete(personel);
        return "personel";
    }

    public void formTemizle() {
        personel = new Personel();
    }

    public PersonelDAO getPersonelDAO() {
        if (this.personelDAO == null) {
            this.personelDAO = new PersonelDAO();
        }
        return personelDAO;
    }

    public void setPersonelDAO(PersonelDAO personelDAO) {
        this.personelDAO = personelDAO;
    }

    public Personel getPersonel() {
        if (this.personel == null) {
            this.personel = new Personel();
        }
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }

    public ArrayList<Personel> getPersonelList() {
        this.personelList = this.getPersonelDAO().findAll(page, pageSize);
        return personelList;
    }

    public void setPersonelList(ArrayList<Personel> personelList) {
        this.personelList = personelList;
    }

    public String getAranan() {
        return aranan;
    }

    public void setAranan(String aranan) {
        this.aranan = aranan;
    }
}
