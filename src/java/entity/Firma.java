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
public class Firma {
    private int id;
    private String  kod;
    private String aciklama;

    public Firma() {
    }

    public Firma(int id, String kod, String aciklama) {
        this.id = id;
        this.kod = kod;
        this.aciklama = aciklama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    @Override
    public String toString() {
        return "Firma{" + "id=" + id + ", kod=" + kod + ", aciklama=" + aciklama + '}';
    }
    
}
