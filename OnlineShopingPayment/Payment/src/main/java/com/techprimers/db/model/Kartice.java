package com.techprimers.db.model;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Kartice {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "tip")
    private String tip;
    @Column(name = "broj")
    private int broj;
    @Column(name = "security_code")
    private int security_code;
    @Column(name = "datum_isteka")
    private String datum_isteka;
    @Column(name = "nosilac_kartice")
    private String nosilac_kartice;
    @Column(name = "korisnik_kartice_id")
    private String korisnik_kartice_id;
    @Column(name = "stanje")
    private int stanje;
    @Column(name="kupovina_id")
    private int kupovina_id;

    @OneToMany(mappedBy = "kartice",cascade = CascadeType.ALL)
    private Set<Kupovina> kupovine;
    public Kartice() {}

    public Kartice(long id,String tip,int broj,int security_code,
                     String datum_isteka,String nosilac_kartice,
                     String korisnik_kartice_id,int stanje,
                     int kupovina_id,Kupovina... kupovine) {
        super();
        this.id=id;
        this.tip=tip;
        this.broj=broj;
        this.security_code=security_code;
        this.datum_isteka=datum_isteka;
        this.nosilac_kartice=nosilac_kartice;
        this.korisnik_kartice_id=korisnik_kartice_id;
        this.stanje=stanje;
        this.kupovina_id=kupovina_id;
        this.kupovine= Stream.of(kupovine).collect(Collectors.toSet());
        this.kupovine.forEach(x->x.setId(this));
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public int getSecurity_code() {
        return security_code;
    }

    public void setSecurity_code(int security_code) {
        this.security_code = security_code;
    }

    public String getDatum_isteka() {
        return datum_isteka;
    }

    public void setDatum_isteka(String datum_isteka) {
        this.datum_isteka = datum_isteka;
    }

    public String getNosilac_kartice() {
        return nosilac_kartice;
    }

    public void setNosilac_kartice(String nosilac_kartice) {
        this.nosilac_kartice = nosilac_kartice;
    }

    public String getKorisnik_kartice_id() {
        return korisnik_kartice_id;
    }

    public void setKorisnik_kartice_id(String korisnik_kartice_id) {
        this.korisnik_kartice_id = korisnik_kartice_id;
    }

    public int getStanje() {
        return stanje;
    }

    public void setStanje(int stanje) {
        this.stanje = stanje;
    }

    public int getKupovina_id() {
        return kupovina_id;
    }

    public void setKupovina_id(int kupovina_id) {
        this.kupovina_id = kupovina_id;
    }
}
