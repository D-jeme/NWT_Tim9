package com.techprimers.db.resource;

import com.techprimers.db.model.Korpa;
import com.techprimers.db.repository.KorpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/rest/korpa")
public class KorpaResource {
    @Autowired
    KorpaRepository korpaRepository;

    @GetMapping(value="/all")
    public ResponseEntity<?> getAll()
    {
        Collection<Korpa> korpa = this.korpaRepository.findAll();
        if (korpa.isEmpty()){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Nema podataka u bazi Korpa");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<Collection<Korpa>>(korpa, HttpStatus.OK);
    }

    @PostMapping(value="/insert")
    public ResponseEntity<?> persist(@RequestBody final Korpa korpa)
    {
        Korpa korpa1=korpaRepository.findById(korpa.getId_korpe());
        if(korpa1==null){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Ne postoji unos");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        if(korpa1.getBroj_proizvoda()==0){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Broj proizvoda ne smije biti jednak nuli!");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        korpaRepository.save(korpa);
        return new ResponseEntity<Collection<Korpa>>(this.korpaRepository.findAll(),HttpStatus.OK);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<?> getOne(@PathVariable int id)
    {
        Korpa korpa=this.korpaRepository.findById(id);
        if(korpa==null){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Ne postoji unos sa idom:"+id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<Korpa>(korpa,HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    ResponseEntity<?> deleteOneRecord(@PathVariable Integer id){
        Korpa korpa=korpaRepository.findOne(id);
        if(korpa==null){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Ne postoji trazeni podatak "+id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        korpaRepository.delete(korpa);
        Map<String,Object> message = new HashMap<String,Object>();
        message.put("MESSAGE","Podatak uspjesno obrisan "+id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateOne(@PathVariable Integer id,@RequestBody final Korpa korpa){
        Korpa korpa1=korpaRepository.findOne(id);
        if(korpa1==null){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Ne postoji trazeni podatak "+id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        korpa1.setBroj_proizvoda(korpa.getBroj_proizvoda());
        korpaRepository.save(korpa1);
        Map<String,Object> message = new HashMap<String,Object>();
        message.put("MESSAGE","Uspjesno izmjenjen broj proizvoda!");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
