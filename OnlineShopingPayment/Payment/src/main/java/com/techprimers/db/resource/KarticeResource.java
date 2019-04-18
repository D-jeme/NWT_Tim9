package com.techprimers.db.resource;

import com.techprimers.db.model.Kartice;
import com.techprimers.db.repository.KarticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/kartice")
public class KarticeResource {

    @Autowired
    KarticeRepository karticeRepository;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll()
    {
        Collection<Kartice> kartice = this.karticeRepository.findAll();
        if (kartice.isEmpty()){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Nema podataka u bazi Kartice");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<Collection<Kartice>>(kartice, HttpStatus.OK);
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<?> persist(@RequestBody final Kartice kartice)
    {
        Kartice kartica=karticeRepository.findById(kartice.getId());
        if(kartica == null){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Vec postoji podatak sam idom:"+kartica.getId());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        if(kartica.getStanje()<0){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Iznos na kartici mora biti pozitivan broj!");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        if(kartica.getTip().equals(null)){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Unesite tip kartice!");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        if(kartica.getSecurity_code()==0){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Unesite sigurnosni kod kartice!");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        if(kartica.getDatum_isteka().equals("")){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Unesite datum isteka kartice!");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        if(kartica.getBroj()==0){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Unesite broj kartice!");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        karticeRepository.save(kartice);
        return new ResponseEntity<Collection<Kartice>>(this.karticeRepository.findAll(),HttpStatus.OK);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<?> getOne(@PathVariable long id){
        Kartice kartice =this.karticeRepository.findById(id);
        if(kartice==null){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Ne postoji unos sa idom:"+id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<Kartice>(kartice,HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    ResponseEntity<?> deleteOneRecord(@PathVariable Long id){
        Kartice kartice =karticeRepository.findOne(id);
        if(kartice==null){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Ne postoji trazeni podatak!"+id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        karticeRepository.delete(kartice);
        Map<String,Object> message = new HashMap<String,Object>();
        message.put("MESSAGE","Podatak uspjesno obrisan"+id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @PutMapping(value="/datum/{id}")
    public ResponseEntity<?> updateDatum(@PathVariable long id,@RequestBody final Kartice kartice){
        Kartice kartica=karticeRepository.findById(id);
        if(kartica==null){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Ne postoji trazeni podatak "+id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        kartica.setDatum_isteka(kartice.getDatum_isteka());
        karticeRepository.save(kartica);
        Map<String,Object> message = new HashMap<String,Object>();
        message.put("MESSAGE","Datum uspjesno izmjenjen "+id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @PutMapping(value="/stanje/{id}")
    public ResponseEntity<?> updateStanje(@PathVariable long id,@RequestBody final Kartice kartice){
        Kartice kartica=karticeRepository.findById(id);
        if(kartica==null){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Ne postoji trazeni podatak "+id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        kartica.setStanje(kartice.getStanje());
        Map<String,Object> message = new HashMap<String,Object>();
        message.put("MESSAGE","Uspjesno izmjenjeno stanje "+id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}

