package com.techprimers.db.resource;

import com.techprimers.db.model.Kupovina;
import com.techprimers.db.repository.KupovinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/rest/kupovina")
public class KupovinaResource {
    @Autowired
    KupovinaRepository kupovinaRepository;

    @GetMapping(value="/all")
    public ResponseEntity<?> getAll()
    {
        Collection<Kupovina> kupovina = this.kupovinaRepository.findAll();
        if (kupovina.isEmpty()){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Nema podataka u bazi Kupovina");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<Collection<Kupovina>>(kupovina, HttpStatus.OK);
    }
    @PostMapping(value="/insert")
    public ResponseEntity<?> persist(@RequestBody final Kupovina kupovina)
    {
        Kupovina kupovina1 =kupovinaRepository.findById(kupovina.getId());
        if(kupovina1==null){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Ne postoji unos sa idom:"+kupovina1.getId());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        if(kupovina1.getKolicina().equals(0)){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Kolicina ne smije biti jednaka 0!");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        if(kupovina1.getDate().equals("")){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Datum ne smije biti prazan!");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        kupovinaRepository.save(kupovina);
        return new ResponseEntity<Collection<Kupovina>>(this.kupovinaRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getOne(@PathVariable int id){
        Kupovina kupovina=this.kupovinaRepository.findById(id);
        if(kupovina==null){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Ne postoji trazeni podatak!"+id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        Map<String,Object> message = new HashMap<String,Object>();
        message.put("MESSAGE","Podatak uspjesno dobavljen "+id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    ResponseEntity<?> deleteOneRecord(@PathVariable Integer id){
        Kupovina kupovina=kupovinaRepository.findOne(id);
        if(kupovina==null){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Ne postoji trazeni podatak!"+id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        kupovinaRepository.delete(kupovina);
        Map<String,Object> message = new HashMap<String,Object>();
        message.put("MESSAGE","Podatak uspjesno obrisan "+id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @PutMapping(value="/datum/{id}")
    public ResponseEntity<?> updateDatum(@PathVariable Integer id,@RequestBody final Kupovina kupovine){
        Kupovina kupovina=kupovinaRepository.findById(id);
        if(kupovina==null){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Ne postoji trazeni podatak!"+id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        kupovina.setDate(kupovine.getDate());
        kupovinaRepository.save(kupovina);
        Map<String,Object> message = new HashMap<String,Object>();
        message.put("MESSAGE","Datum uspjesno promjenjen "+id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @PutMapping(value="/kolicina/{id}")
    public ResponseEntity<?> updatekolicina(@PathVariable int id,@RequestBody final Kupovina kupovine){
        Kupovina kupovina=kupovinaRepository.findById(id);
        if(kupovina==null){
            Map<String,Object> message = new HashMap<String,Object>();
            message.put("MESSAGE","Ne postoji trazeni podatak!"+id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        kupovina.setKolicina(kupovine.getKolicina());
        kupovinaRepository.save(kupovina);
        Map<String,Object> message = new HashMap<String,Object>();
        message.put("MESSAGE","Kolicina uspjesno promjenjena "+id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
