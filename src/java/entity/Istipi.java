/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author DAMLAC
 */
public class Istipi {
     private int id;
    private String  kod;
    private String aciklama;

    public Istipi() {
    }

    public Istipi(int id, String kod, String aciklama) {
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
        return "Istipi{" + "id=" + id + ", kod=" + kod + ", aciklama=" + aciklama + '}';
    }
}
