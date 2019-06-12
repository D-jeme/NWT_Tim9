package com.techprimers.db.resource;

import com.techprimers.db.model.Pictures;
import com.techprimers.db.repository.PicturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.core.io.support.ResourcePatternUtils.isUrl;

@RestController

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/pictures")
public class PicturesResource {

    @Autowired
    PicturesRepository picturesRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/")
    public List<Pictures> getAll() {
        return picturesRepository.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
   @GetMapping(value = "/{id}")
    public ResponseEntity<?> getArtical(@PathVariable Integer id) {

        Map<String, Object> message = new HashMap<String, Object>();
        Pictures pictures = this.picturesRepository.findByBroj(id);
        if (pictures == null) {

            message.put("MESSAGE", "Ne postoji slika sa id " + id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<Pictures>(pictures, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> persist(@RequestBody final Pictures pictures) {
        System.out.println("Ima liiiiiiiii MEEEEE"+pictures);
        System.out.println("SLIKAAAAAAA"+pictures.getSlika());
        Map<String, Object> message = new HashMap<String, Object>();
        if (pictures.getSlika() == "" || pictures.getSlika() == null){
            System.out.println("POLJEEEE PUNOOOOO");
            message.put("MESSAGE", "Polje slika se mora popuniti");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        if (!isUrl(pictures.getSlika())){
            System.out.println("POLJEEEEE PRAZNOOOOO");
            message.put("MESSAGE", "Polje slika se mora biti URL");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        System.out.println("TU SAMMMM1111");
        picturesRepository.save(pictures);
        System.out.println("TU SAMMMM");
        return new ResponseEntity<Collection<Pictures>>(this.picturesRepository.findAll(), HttpStatus.OK);
    }


    @PostMapping(value = "/picture")
    public String CreatePicture(@RequestBody final Pictures pictures) {
        System.out.println("Ima liiiiiiiii MEEEEE"+pictures);
        System.out.println("SLIKAAAAAAA"+pictures.getSlika());
        Map<String, Object> message = new HashMap<String, Object>();
        if (pictures.getSlika() == "" || pictures.getSlika() == null){
            System.out.println("POLJEEEE PUNOOOOO");
            message.put("MESSAGE", "Polje slika se mora popuniti");
            return "Nije kreiranaa";
        }

        if (!isUrl(pictures.getSlika())){
            System.out.println("POLJEEEEE PRAZNOOOOO");
            message.put("MESSAGE", "Polje slika se mora biti URL");
            return "Nije kreirana";
        }


        picturesRepository.save(pictures);

        System.out.println("TU SAMMMM");

        return "Kreirana";

    }






    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Integer id) {

        Map<String, Object> message = new HashMap<String, Object>();
        Pictures pictures = picturesRepository.findByBroj(id);
        if (pictures == null){

            message.put("MESSAGE","Ne postoji slika u bazi sa id " + id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        picturesRepository.delete(pictures);

        message.put("MESSAGE", "Uspjesno obrisan artikal " + id);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }


    @PostMapping("/exist")
    Boolean PictureByURLExists(@RequestBody final String slika) {
        System.out.println("EXISTTTTT"+slika);
        Pictures picture=picturesRepository.findBySlika(slika);
        System.out.println("SLIKAAAAA"+picture);
        if(picture==null){
            return false;
        }
        return true;
    }

    @PostMapping("/picture_id")
    Integer PictureByURL(@RequestBody final String slika) {
        System.out.println("PICTUREEEEEE"+slika);
        Pictures picture=picturesRepository.findBySlika(slika);
        System.out.println("SLIKAAAAA"+picture.getBroj());
        if(picture==null){
            return -1;
        }
        System.out.println("BROOOoooJ"+picture);
        return (picture.getBroj());
    }

    @PostMapping("/picture_id1")
    Integer PictureByURL1(@RequestBody final String slika) {
        Pictures pic=new Pictures(slika);
       Pictures pic2= picturesRepository.save(pic);
        System.out.println("PICTUREEEEEE"+pic2);
        System.out.println("SLIKAAAAA"+pic2.getBroj());
        if(pic2==null){
            return -1;
        }
        System.out.println("BROOOoooJ"+pic2);
        return (pic2.getBroj());
    }

}
