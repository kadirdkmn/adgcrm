/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ABDULKADIR
 */
public class GenelIs {
    private int id;
    private Personel personel;
    private Firma firma;
    private Istipi isTipi;
    private Durum durum;
    private Oncelik oncelik;
    private PlanDurumu planDurumu;
    private Program program;
    private String isdetay;
    private String Ekran;
    private String bolum;

    public GenelIs() {
    }

    public GenelIs(int id, Personel personel, Firma firma, Istipi isTipi, Durum durum, Oncelik oncelik, PlanDurumu planDurumu, Program program, String isdetay, String Ekran, String bolum) {
        this.id = id;
        this.personel = personel;
        this.firma = firma;
        this.isTipi = isTipi;
        this.durum = durum;
        this.oncelik = oncelik;
        this.planDurumu = planDurumu;
        this.program = program;
        this.isdetay = isdetay;
        this.Ekran = Ekran;
        this.bolum = bolum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public Istipi getIsTipi() {
        return isTipi;
    }

    public void setIsTipi(Istipi isTipi) {
        this.isTipi = isTipi;
    }

    public Durum getDurum() {
        return durum;
    }

    public void setDurum(Durum durum) {
        this.durum = durum;
    }

    public Oncelik getOncelik() {
        return oncelik;
    }

    public void setOncelik(Oncelik oncelik) {
        this.oncelik = oncelik;
    }

    public PlanDurumu getPlanDurumu() {
        return planDurumu;
    }

    public void setPlanDurumu(PlanDurumu planDurumu) {
        this.planDurumu = planDurumu;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public String getIsdetay() {
        return isdetay;
    }

    public void setIsdetay(String isdetay) {
        this.isdetay = isdetay;
    }

    public String getEkran() {
        return Ekran;
    }

    public void setEkran(String Ekran) {
        this.Ekran = Ekran;
    }

    public String getBolum() {
        return bolum;
    }

    public void setBolum(String bolum) {
        this.bolum = bolum;
    }

    
    
}
