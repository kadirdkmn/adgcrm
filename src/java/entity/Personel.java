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
public class Personel {
    private int id;
    private String personelad;
    private String personelsoyad;
    private String personelcinsiyet;
    private String aciklama;

    public Personel() {
    }

    public Personel(int id, String personelad, String personelsoyad, String personelcinsiyet, String aciklama) {
        this.id = id;
        this.personelad = personelad;
        this.personelsoyad = personelsoyad;
        this.personelcinsiyet = personelcinsiyet;
        this.aciklama = aciklama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonelad() {
        return personelad;
    }

    public void setPersonelad(String personelad) {
        this.personelad = personelad;
    }

    public String getPersonelsoyad() {
        return personelsoyad;
    }

    public void setPersonelsoyad(String personelsoyad) {
        this.personelsoyad = personelsoyad;
    }

    public String getPersonelcinsiyet() {
        return personelcinsiyet;
    }

    public void setPersonelcinsiyet(String personelcinsiyet) {
        this.personelcinsiyet = personelcinsiyet;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    @Override
    public String toString() {
        return "Personel{" + "id=" + id + ", personelad=" + personelad + ", personelsoyad=" + personelsoyad + ", personelcinsiyet=" + personelcinsiyet + ", aciklama=" + aciklama + '}';
    }

    
    
    
 }
