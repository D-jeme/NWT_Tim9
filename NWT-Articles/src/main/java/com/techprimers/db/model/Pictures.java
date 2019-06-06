package com.techprimers.db.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pictures {

    @Id
    @GeneratedValue
    @Column(name = "broj")
    private Integer broj;

    @URL
    @Column(name = "slika")
    private String slika;


    public Pictures() {

    }

    public Pictures(String slika) {
        this.setSlika(slika);
    }

    public Integer getBroj() {
        return broj;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
}
