/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProgramDAO;
import entity.Program;
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
public class ProgramBean implements Serializable {

    private ProgramDAO programDAO;
    private Program program;
    private ArrayList<Program> programList;
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
        programList = getProgramDAO().findAll(page, pageSize);
    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }

        programList = getProgramDAO().findAll(page, pageSize);
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

    public String delete(Program program) {
        getProgramDAO().delete(program);
        return "program";
    }

    public ProgramBean() {
    }

    public String create() {
        this.getProgramDAO().create(program);
        program = new Program();
        return "program";
    }

    public String update() {
        getProgramDAO().update(program);
        program = new Program();
        return "program";
    }

    public void search() {
        System.out.println("controller.ProgramBean.search()");
        programList = new ArrayList<>();
        // istipiList = getProgramDAO().search(aranan);
    }

    public void fullList() {
        programList = getProgramDAO().findAll(page, pageSize);
    }

    public void ProgramSec(Program prg) {
        program = prg;
    }

    public void formTemizle() {
        program = new Program();
    }

    public ProgramDAO getProgramDAO() {
        if (this.programDAO == null) {
            this.programDAO = new ProgramDAO();
        }
        return programDAO;
    }

    public void setProgramDAO(ProgramDAO programDAO) {
        this.programDAO = programDAO;
    }

    public Program getProgram() {
        if (this.program == null) {
            this.program = new Program();
        }
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public ArrayList<Program> getProgramList() {
        this.programList = this.getProgramDAO().findAll(page, pageSize);
        return programList;
    }

    public void setProgramList(ArrayList<Program> programList) {
        this.programList = programList;
    }

    public String getAranan() {
        return aranan;
    }

    public void setAranan(String aranan) {
        this.aranan = aranan;
    }
}
