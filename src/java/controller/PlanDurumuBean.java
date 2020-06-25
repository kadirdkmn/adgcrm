/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PlanDurumuDAO;
import entity.PlanDurumu;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author GULAYOTMAN
 */
@Named
@SessionScoped
public class PlanDurumuBean implements Serializable{
    private PlanDurumuDAO planDurumuDAO;
    private PlanDurumu planDurumu;
    private ArrayList<PlanDurumu> planDurumuList;
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
        planDurumuList = getPlanDurumuDAO().findAll(page, pageSize);
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }

        planDurumuList = getPlanDurumuDAO().findAll(page, pageSize);
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
    
    public PlanDurumuBean() {
        fullList();
    }
    
    public String create() {
        this.getPlanDurumuDAO().create(planDurumu);
        planDurumu = new PlanDurumu();
        return "plandurum";
    }
    
    public String update() {
        getPlanDurumuDAO().update(planDurumu);
        planDurumu = new PlanDurumu();
        return "plandurum";
    }
    
    public void search(){
        System.out.println("controller.PlanDurumuBean.search()");
        planDurumuList = new ArrayList<>();
        // istipiList = getPlanDurumuDAO().search(aranan);
    }
    public String delete(PlanDurumu plandurumu) {
        getPlanDurumuDAO().delete(plandurumu);
        return "plandurum";
    }
    public void fullList(){
       planDurumuList = getPlanDurumuDAO().findAll(page,pageSize);
    }
    
    public void planDurumuSec(PlanDurumu pd) {
        planDurumu = pd;
    }
    
    public void formTemizle() {
        planDurumu = new PlanDurumu();
    }

    public PlanDurumuDAO getPlanDurumuDAO() {
        if(this.planDurumuDAO == null)
            this.planDurumuDAO = new PlanDurumuDAO();
        return planDurumuDAO;
    }

    public void setPlanDurumuDAO(PlanDurumuDAO planDurumuDAO) {
        this.planDurumuDAO = planDurumuDAO;
    }

    public PlanDurumu getPlanDurumu() {
        if(this.planDurumu == null)
            this.planDurumu = new PlanDurumu();
        return planDurumu;
    }

    public void setPlanDurumu(PlanDurumu planDurumu) {
        this.planDurumu = planDurumu;
    }

    public ArrayList<PlanDurumu> getPlanDurumuList() {
        this.planDurumuList = this.getPlanDurumuDAO().findAll(page, pageSize);
        return planDurumuList;
    }

    public void setPlanDurumuList(ArrayList<PlanDurumu> planDurumuList) {
        this.planDurumuList = planDurumuList;
    }

    public String getAranan() {
        return aranan;
    }

    public void setAranan(String aranan) {
        this.aranan = aranan;
    }
}
