package com.techprimers.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Articles {

    @Id
    @GeneratedValue
    @Column(name = "broj")
    private Integer broj;
    @Column(name = "naziv")
    private String naziv;
    @Column(name = "kratki_tekst")
    private String kratki_tekst;
    @Column(name="dugi_tekst")
    private String dugi_tekst;
    @Column(name = "cijena")
    private Integer cijena;
    @Column(name = "aktivan")
    private boolean aktivan;
    @Column(name = "kolicina")
    private Integer kolicina;
    @Column (name ="popust")
    private Integer popust;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "slika")
    private Pictures pictures;

    public Articles() {
    }

    public Integer getBroj() {
        return broj;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }


    public Pictures getPictures() {
        return pictures;
    }

    public void setPictures(Pictures pictures) {
        this.pictures = pictures;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getKratki_tekst() {
        return kratki_tekst;
    }

    public void setKratki_tekst(String kratki_tekst) {
        this.kratki_tekst = kratki_tekst;
    }

    public String getDugi_tekst() {
        return dugi_tekst;
    }

    public void setDugi_tekst(String dugi_tekst) {
        this.dugi_tekst = dugi_tekst;
    }

    public Integer getCijena() {
        return cijena;
    }

    public void setCijena(Integer cijena) {
        this.cijena = cijena;
    }

    public Boolean getAktivan(){ return aktivan;}

    public void setAktivan(Boolean aktivan){ this.aktivan=aktivan;}

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }


    public Integer getPopust() {
        return popust;
    }

    public void setPopust(Integer popust) {
        this.popust = popust;
    }

}
