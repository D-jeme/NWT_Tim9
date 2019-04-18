package com.techprimers.db.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Kupovina {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "proizvod_id")
    private Integer proizvod_id;
    @Column(name = "kolicina")
    private Integer kolicina;
    @Column(name = "datum")
    private String datum;


    @ManyToOne
    @JoinColumn
    private Korpa korpa;

    @ManyToOne
    @JoinColumn
    private Kartice kartice;
    public Kupovina(){}

    public Kupovina(Integer id,Integer proizvod_id,Integer kolicina,String datum,Integer kartica_id) {
        this.id=id;
        this.proizvod_id=proizvod_id;
        this.kolicina=kolicina;
        this.datum=datum;

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getProizvod_id() {
        return proizvod_id;
    }

    public void setProizvod_id(Integer proizvod_id) {
        this.proizvod_id = proizvod_id;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public String getDate() {
        return datum;
    }

    public void setDate(String date) {
        this.datum=datum;
    }

    public void setId(Korpa korpa) {
        this.id=id;
    }
    public void SetId(Kartice kartice){
        this.id=id;
    }

    public void setId(Kartice kartice) {
        this.kartice=kartice;
    }
}
